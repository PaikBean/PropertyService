import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid from '@/components/datagrid/CustomDataGrid'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import SaveTogleToolbar from '@/components/toolbar/SaveTogleToolbar'
import { Box, Divider, Grid, IconButton, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import clientColumns from './columns/ClientColumns'
import { fetchSearchClients } from './api/fetchSearchClients'
import { fetchSearchClient } from './api/fetchSearchClient'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import AddRemarkModal from '@/components/modal/AddRemarkModal'
import remarkColumns from './columns/RemarkColumns'

const ManageClientPage = () => {
  const initialSearchCondition = {
    clientName: '',
    clientPhoneNumber: '',
  }

  const initialSearchData = {
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

  const handleCloseModal = () => {
    isRemarkModalOpen ? setIsRemarkModalOpen(false) : null
  }

  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleDeleteRemarkRows = () => {
    const newShowingRows = remarkRows.filter(
      (row) => !selectedRemarkRowIds.includes(row.id)
    )
    setRemarkRows(newShowingRows)
    setSelectedRemarkRowIds([])
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
      console.log(searchCondition)
      const response = await fetchSearchClients(searchCondition)
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
    setClientId(params.id)
  }

  const handleSave = () => {}

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
                      <InputPhoneNumber
                        value={searchCondition.clientPhoneNumber}
                        onChange={(formattedPhoneNumber) =>
                          handleSearchInputChange(
                            'clientPhoneNumber',
                            formattedPhoneNumber
                          )
                        }
                        sx={{
                          '.MuiInputBase-input': { height: '10px' },
                          '.MuiInputLabel-root': {
                            top: '-6px',
                          },
                        }}
                        name="clientPhoneNumber"
                        label="고객 전화번호"
                      />
                    </Stack>
                  </Grid>
                  <Grid item xs={3} display="flex" alignItems="center">
                    <SearchBtn onClick={handleSearch} height="100px" />
                  </Grid>
                </Grid>
                <Grid item>
                  <CustomDataGrid
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
                    <InputName2
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
                    <InputName2
                      label="담당 매니저"
                      value={searchData.picManger}
                      onChange={(e) => {
                        handleInputChange('picManger', e.target.value)
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
                </Grid>
                <Grid sx={{ borderTop: '1.5px solid Black' }} />
                <Grid container>
                  <Grid item xs={11}>
                    <CustomDataGrid
                      rows={remarkRows}
                      columns={remarkColumns}
                      height={'65vh'}
                      columnVisibilityModel={{
                        remarkId: false,
                      }}
                      checkboxSelection={true}
                      onRowSelectionModelChange={handleRemarkRows}
                      showAll={true}
                      pageSize={10}
                      rowHeight={48}
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
