import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid from '@/components/datagrid/CustomDataGrid'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import SaveTogleToolbar from '@/components/toolbar/SaveTogleToolbar'
import { Box, Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Divider, Grid, IconButton, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import clientColumns from './columns/ClientColumns'
import { fetchSearchClients } from './api/fetchSearchClients'
import { fetchSearchClient } from './api/fetchSearchClient'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import AddRemarkModal from '@/components/modal/AddRemarkModal'
import remarkColumns from './columns/RemarkColumns'
import CustomDataGrid2 from '@/components/datagrid/CustomDataGrid2'
import ManagerAutocomplete from '@/components/autocomplete/ManagerAutocomplete'
import { fetchDeleteClientRemark } from './api/fetchDeleteClientRemark'
import { fetchUpdateClient } from './api/fetchUpdateClient'
import InflowType from '@/components/autocomplete/InflowType'

const ManageClientPage = () => {
  const initialSearchCondition = {
    clientName: '',
    clientPhoneNumber: '',
  }

  const initialSearchData = {
    clientId: null,
    clientName: '',
    inflowType: '',
    clientPhoneNumber: '',
    picManger: '',
    remarkList: [],
  }

  const [mode, setMode] = useState(false)

  const [clientId, setClientId] = useState(null)

  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)
  const [searchData, setSearchData] = useState(initialSearchData)

  const [clientRows, setClientRows] = useState([])
  const [remarkRows, setRemarkRows] = useState([])

  const [selectedRemarkRowIds, setSelectedRemarkRowIds] = useState([])

  const [isRemarkModalOpen, setIsRemarkModalOpen] = useState(false)

  const handleCloseModal = async () => {
    try {
      const response = await fetchSearchClient(clientId)
      if (response.responseCode === 'SUCCESS') {
        setSearchData(response.data)
        setRemarkRows(response.data.clientRemarkList)
      } else {
        throw new Error('Failed to fetch client list:', response.message)
      }
    } catch (error) {
      alert('Error fetching client list:', error)
    }
    isRemarkModalOpen ? setIsRemarkModalOpen(false) : null
  }

  const handleInputChange = (field, value) => {
    setSearchData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }
  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleDeleteRemarkRows = async () => {
    console.log(selectedRemarkRowIds)
    try{
      const response = await fetchDeleteClientRemark(selectedRemarkRowIds[0])
      if(response.responseCode === "SUCCESS"){
        const response = await fetchSearchClient(clientId)
        if (response.responseCode === 'SUCCESS') {
          setSearchData(response.data)
          setRemarkRows(response.data.clientRemarkList)
        } else {
          throw new Error('Failed to fetch client list:', response.message)
        }
      } else{
        throw new Error('Failed to fetch:', response.message)
      }
    } catch(error){
      alert(error)
    }
    // const newShowingRows = remarkRows.filter(
    //   (row) => !selectedRemarkRowIds.includes(row.id)
    // )
    // setRemarkRows(newShowingRows)
    // setSelectedRemarkRowIds([])
  }

  const handleRemarkRows = (newSelection) => {
    setSelectedRemarkRowIds(newSelection)
  }

  useEffect(() => {
    const fetchClientData = async () => {
      if (clientId) {
        try {
          const response = await fetchSearchClient(clientId)
          if (response.responseCode === 'SUCCESS') {
            setSearchData(response.data)
            setRemarkRows(response.data.clientRemarkList)
          } else {
            console.error('Failed to fetch client list:', response.message)
          }
        } catch (error) {
          console.error('Error fetching client list:', error)
        }
      }
    }
    fetchClientData()
  }, [clientId])

  const handleSearch = async () => {
    try {
      const response = await fetchSearchClients(searchCondition.clientName, searchCondition.clientPhoneNumber)
      if (response.responseCode === 'SUCCESS') {
        setClientRows(response.data)
      } else {
        console.error('Failed to fetch client list:', response.message)
      }
    } catch (error) {
      console.error('Error fetching client list:', error)
    }
  }

  const handleSelectClientRow = async (params) => {
    setClientId(params[0])
  }

  const handleSave = async () => {
    console.log(searchData)
    try {
      const response = await fetchUpdateClient(searchData)
      if(response.responseCode === "SUCCESS"){
        const response = await fetchSearchClient(clientId)
        if (response.responseCode === 'SUCCESS') {
          setSearchData(response.data)
          setRemarkRows(response.data.clientRemarkList)
        } else {
          throw new Error('Failed to fetch client list:', response.message)
        }
      } else{
        throw new Error('Failed to fetch:', response.message)
      }
    } catch (error) {
      console.error('Error fetching client list:', error)
    }
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SaveTogleToolbar
          text={'고객 관리'}
          onChange={() => {
            setMode(!mode)
          }}
          onClick={handleSave}
        ></SaveTogleToolbar>
        <Stack spacing={4}>
          <Divider></Divider>
          <Grid
            container
            gap={2}
            sx={{
              '& .revenu-header-css': {
                backgroundColor: 'lightgrey',
              },
            }}
          >
            <Grid item xs={3}>
              <Stack spacing={1}>
                <Grid container gap={1}>
                  <Grid item xs={8.5}>
                    <Stack spacing={2}>
                      <InputName2
                        label="고객"
                        value={searchCondition.clientName}
                        onChange={(e) => {
                          handleSearchInputChange('clientName', e.target.value)
                        }}
                        sx={{
                          '.MuiInputBase-input': { height: '10px' },
                          '.MuiInputLabel-root': {
                            top: '-6px',
                          },
                        }}
                        name="clientName"
                      />
                       <InputName2
                        label="고객 전화번호"
                        value={searchCondition.clientPhoneNumber}
                        onChange={(e) => {
                          handleSearchInputChange('clientPhoneNumber', e.target.value)
                        }}
                        sx={{
                          '.MuiInputBase-input': { height: '10px' },
                          '.MuiInputLabel-root': {
                            top: '-6px',
                          },
                        }}
                        name="clientPhoneNumber"
                      />
                    </Stack>
                  </Grid>
                  <Grid item xs={3} display="flex" alignItems="center">
                    <SearchBtn onClick={handleSearch} height="100px" />
                  </Grid>
                </Grid>
                <Grid item>
                  <CustomDataGrid2
                    rows={clientRows}
                    columns={clientColumns}
                    height={'63vh'}
                    columnVisibilityModel={{
                      clientId: false,
                    }}
                    showAll={true}
                    onRowSelectionModelChange={handleSelectClientRow}
                    pageSize={10}
                    rowHeight={48}
                    getRowId={(row) => row.clientId}
                  />
                </Grid>
              </Stack>
            </Grid>
            <Grid item xs={8.5}>
              <Stack spacing={2}>
                <Grid container gap={5}>
                  <Grid item xs={2}>
                    <InputName2
                      label="고객"
                      value={searchData.clientName}
                      onChange={(e) => {
                        handleInputChange('clientName', e.target.value)
                      }}
                      name="clientName"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={2}>
                    {/* <InputName2
                      label="유입경로"
                      value={searchData.inflowType}
                      onChange={(e) => {
                        handleInputChange('inflowType', e.target.value)
                      }}
                      name="clientName"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                    /> */}
                    <InflowType
                      value={searchData.inflowType} // Add this line
                      onChange={(value) => {
                        handleInputChange('inflowType', value)
                      }}
                      label="유입 경로"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={3}>
                    <InputPhoneNumber
                      value={searchData.clientPhoneNumber}
                      onChange={(formattedPhoneNumber) =>
                        handleInputChange(
                          'clientPhoneNumber',
                          formattedPhoneNumber
                        )
                      }
                      name="clientPhoneNumber"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                      label="고객 전화번호"
                    />
                  </Grid>
                  <Grid item xs={2}>
                  <ManagerAutocomplete
                      value={searchData.managerId} // Add this line
                      onChange={(value) => {
                        handleInputChange('managerId', value)
                      }}
                      label="담당 매니저"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                </Grid>
                <Grid sx={{ borderTop: '1.5px solid Black' }} />
                <Grid container>
                  <Grid item xs={11}>
  
                    <CustomDataGrid2
                    rows={remarkRows}
                    columns={remarkColumns}
                    height={'65vh'}
                    columnVisibilityModel={{
                      clientRemarkId: false,
                    }}
                    showAll={true}
                    onRowSelectionModelChange={handleRemarkRows}
                    pageSize={10}
                    rowHeight={48}
                    getRowId={(row) => row.clientRemarkId} // getRowId 속성 전달
                  />
                  </Grid>
                  <Grid
                    xs={1}
                    item
                    display="flex"
                    alignItems="center"
                    justifyContent="center"
                  >
                    {mode && (
                      <Stack direction="column" spacing={2}>
                        <IconButton
                          onClick={() => {
                            setIsRemarkModalOpen(!isRemarkModalOpen)
                          }}
                          size="large"
                        >
                          <AddIcon fontSize="large" />
                        </IconButton>
                        <IconButton
                          onClick={handleDeleteRemarkRows}
                          size="large"
                        >
                          <RemoveIcon fontSize="large" />
                        </IconButton>
                      </Stack>
                    )}
                  </Grid>
                </Grid>
              </Stack>
            </Grid>
          </Grid>
        </Stack>
      </Stack>
      <AddRemarkModal
        open={isRemarkModalOpen}
        handleClose={handleCloseModal}
        data={{ clientId: clientId, managerId: searchData.managerId }}
      />
    </Box>
  )
}

ManageClientPage.displayName = 'ManageClientPage'

export default ManageClientPage
