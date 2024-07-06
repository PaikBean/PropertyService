import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import InflowType from '@/components/autocomplete/InflowType'
import ManagerAutocomplete from '@/components/autocomplete/ManagerAutocomplete'
import TransactionType from '@/components/autocomplete/TransactionType'
import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid from '@/components/datagrid/CustomDataGrid'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import RemarkTextField from '@/components/textfield/RemarkTextField'
import SaveToolbar from '@/components/toolbar/SaveToolbar'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import {
  Alert,
  Divider,
  Grid,
  IconButton,
  Snackbar,
  Typography,
} from '@mui/material'
import { Box, Stack } from '@mui/system'
import { useEffect, useState } from 'react'
import { fetchSearchProperties } from './api/fetchSearchProperties'
import { fetchRegistClient } from './api/fetchRegistClient'
import propertyColumns from './columns/PropertyColumns'

const RegistClientPage = () => {
  const initialData = {
    clientName: '',
    inflowTypeId: null,
    clientPhoneNumber: '',
    managerId: null,
    remark: '',
    propertyList: [],
  }
  const initialSearchCondition = {
    addressL1: null,
    addressL2: null,
    transactionTypeId: null,
    propertyTypeId: null,
  }

  const [registData, setRegistData] = useState(initialData)
  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)

  const [searchRows, setSearchRows] = useState([])
  const [showingRows, setShowingRows] = useState([])
  const [selectedSearchRowIds, setSelectedSearchRowIds] = useState([])
  const [selectedShowingRowIds, setSelectedShowingRowIds] = useState([])

  const [openSuccessSnackbar, setOpenSuccessSnackbar] = useState(false)
  const [openErrorSnackbar, setOpenErrorSnackbar] = useState(false)
  const [errorMessage, setErrorMessage] = useState('')

  useEffect(() => {
    console.log(registData)
  }, [registData])

  const handleInputChange = (field, value) => {
    setRegistData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearchConditionInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearch = async () => {
    try {
      console.log(searchCondition)
      const response = await fetchSearchProperties(searchCondition)
      if (response.responseCode === 'SUCCESS') {
        setSearchRows(response.data)
      } else {
        console.error('Failed to fetch revenue list:', response.message)
      }
    } catch (error) {
      console.error('Error fetching revenue list:', error)
    }
  }

  const handleInsertShowingRows = () => {
    const selectedRows = searchRows.filter((row) => {
      return selectedSearchRowIds.includes(row.id)
    })
    setShowingRows(selectedRows)
    setSelectedSearchRowIds([])
  }
  const handleDeleteShowingRows = () => {
    const newShowingRows = showingRows.filter(
      (row) => !selectedShowingRowIds.includes(row.id)
    )
    setShowingRows(newShowingRows)
    setSelectedShowingRowIds([])
  }

  const handleSelectSearchRows = (newSelection) => {
    setSelectedSearchRowIds(newSelection)
  }
  const handleSelectShowingRows = (newSelection) => {
    setSelectedShowingRowIds(newSelection)
  }

  const handleSuccessSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    openSuccessSnackbar ? setOpenSuccessSnackbar(false) : null
  }

  const handleErrorSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    openErrorSnackbar ? setOpenErrorSnackbar(false) : null
  }

  const handleSave = async () => {
    try {
      handleInputChange('propertyList', showingRows)
      const result = await fetchRegistClient(registData)
      setOpenSuccessSnackbar(true)
      setRegistData(initialData)
      setSearchCondition(initialSearchCondition)
      setSearchRows([])
      setShowingRows([])
    } catch (error) {
      setErrorMessage(error.message)
      setOpenErrorSnackbar(true)
    }
    return
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SaveToolbar text="고객 등록" onClick={handleSave}></SaveToolbar>
        <Divider></Divider>
        <Stack spacing={3}>
          <Divider sx={{ width: '100%', borderColor: 'white' }}></Divider>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <Grid item xs={2.5}>
              <InputName2
                label="고객"
                value={registData.clientName}
                onChange={(e) => {
                  handleInputChange('clientName', e.target.value)
                }}
                name="clientName"
              />
            </Grid>
            <Grid item xs={2.5}>
              <InflowType
                value={registData.inflowTypeId} // Add this line
                onChange={(value) => {
                  handleInputChange('inflowTypeId', value)
                }}
                label="유입 경로"
              />
            </Grid>
            <Grid item xs={2.5}>
              <InputPhoneNumber
                value={registData.clientPhoneNumber}
                onChange={(formattedPhoneNumber) =>
                  handleInputChange('clientPhoneNumber', formattedPhoneNumber)
                }
                name="clientPhoneNumber"
                label="고객 전화번호"
              />
            </Grid>
            <Grid item xs={2.5}>
              <ManagerAutocomplete
                value={registData.managerId} // Add this line
                onChange={(value) => {
                  handleInputChange('managerId', value)
                }}
                label="담당 매니저"
              />
            </Grid>
          </Grid>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <Grid item xs>
              <RemarkTextField
                value={registData.remark}
                onChange={(e) => {
                  handleInputChange('remark', e.target.value)
                }}
                rows={3}
              />
            </Grid>
          </Grid>
          <Grid
            container
            sx={{
              '& .revenu-header-css': {
                backgroundColor: 'lightgrey',
              },
            }}
          >
            <Grid item xs={5.5}>
              <Stack spacing={2}>
                <Grid container gap={1}>
                  <Grid item xs={3}>
                    <AddressL1
                      value={searchCondition.addressL1} // Add this line
                      onChange={(value) => {
                        handleSearchConditionInputChange('addressL1', value)
                      }}
                      label="시/도"
                    />
                  </Grid>
                  <Grid item xs={4}>
                    <AddressL2
                      value={searchCondition.addressL2} // Add this line
                      addressLevel1={searchCondition.addressL1}
                      onChange={(value) => {
                        handleSearchConditionInputChange('addressL2', value)
                      }}
                      label="시/구/동"
                    />
                  </Grid>
                  <Grid item xs={2}>
                    <TransactionType
                      value={searchCondition.transactionTypeId} // Add this line
                      onChange={(value) => {
                        handleSearchConditionInputChange(
                          'transactionTypeId',
                          value
                        )
                      }}
                    />
                  </Grid>
                  <Grid
                    item
                    xs={1}
                    style={{ display: 'flex', justifyContent: 'center' }}
                  >
                    <SearchBtn onClick={handleSearch} />
                  </Grid>
                </Grid>
                <Grid item>
                  <CustomDataGrid
                    rows={searchRows}
                    columns={propertyColumns}
                    height={'43vh'}
                    columnVisibilityModel={{
                      propertyId: false,
                    }}
                    checkboxSelection={true}
                    onRowSelectionModelChange={handleSelectSearchRows}
                    showAll={true}
                    pageSize={10}
                    rowHeight={48}
                  />
                </Grid>
              </Stack>
            </Grid>
            <Grid
              item
              display="flex"
              alignItems="center"
              justifyContent="center"
            >
              <Stack direction="column" spacing={2}>
                <IconButton onClick={handleInsertShowingRows} size="large">
                  <AddIcon fontSize="large" />
                </IconButton>
                <IconButton onClick={handleDeleteShowingRows} size="large">
                  <RemoveIcon fontSize="large" />
                </IconButton>
              </Stack>
            </Grid>
            <Grid item xs={5.5}>
              <CustomDataGrid
                rows={showingRows}
                columns={propertyColumns}
                height={'50.8vh'}
                columnVisibilityModel={{
                  propertyId: false,
                }}
                checkboxSelection={true}
                onRowSelectionModelChange={handleSelectShowingRows}
                showAll={true}
                pageSize={10}
                rowHeight={48}
              />
            </Grid>
          </Grid>
        </Stack>
      </Stack>
      <Snackbar
        open={openSuccessSnackbar}
        autoHideDuration={5000}
        onClose={handleSuccessSnackbarClose}
      >
        <Alert
          onClose={handleSuccessSnackbarClose}
          severity="success"
          variant="filled"
          sx={{ width: '100%' }}
        >
          고객 저장 완료
        </Alert>
      </Snackbar>
      <Snackbar
        open={openErrorSnackbar}
        autoHideDuration={5000}
        onClose={handleErrorSnackbarClose}
      >
        <Alert
          onClose={handleErrorSnackbarClose}
          severity="error"
          variant="filled"
          sx={{ width: '100%' }}
        >
          {errorMessage}
        </Alert>
      </Snackbar>
    </Box>
  )
}

RegistClientPage.displayName = 'RegistClientPage'

export default RegistClientPage
