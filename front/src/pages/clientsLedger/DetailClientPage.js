import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid from '@/components/datagrid/CustomDataGrid'
import InputName from '@/components/textfield/InputName'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import SingleToolbar from '@/components/toolbar/SingleToolbar'
import { Widgets } from '@mui/icons-material'
import { Box, Divider, Grid, IconButton, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import clientColumns from './columns/ClientColumns'
import propertyColumns from './columns/PropertyColumns'
import remarkColumns from './columns/RemarkColumns'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import { fetchSearchClients } from './api/fetchSearchClients'
import { fetchSearchClient } from './api/fetchSearchClient'
import scheduleColumns from './columns/ScheduleColumns'
import AddScheduleModal from '@/components/modal/AddScheduleModal'
import AddRemarkModal from '@/components/modal/AddRemarkModal'
import AddPropertyModal from '@/components/modal/AddPropertyModal'

const DetailClientPage = () => {
  const initialSearchCondition = {
    clientName: '',
    clientPhoneNumber: '',
  }
  const initialSearchData = {
    clientName: '',
    inflowType: '',
    clientPhoneNumber: '',
    picManger: '',
    scheduleList: [],
    showingPropertyList: [],
    remarkList: [],
  }

  const [clientId, setClientId] = useState(null)

  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)
  const [searchData, setSearchData] = useState(initialSearchData)

  const [clientRows, setClientRows] = useState([])
  const [scheduleRows, setScheduleRows] = useState([])
  const [propertyRows, setPropertyRows] = useState([])
  const [remarkRows, setRemarkRows] = useState([])

  const [selectedScheduleRowIds, setSelectedScheduleRowIds] = useState([])
  const [selectedPropertyRowIds, setSelectedPropertyRowIds] = useState([])
  const [selectedRemarkRowIds, setSelectedRemarkRowIds] = useState([])

  const [isScheduleModalOpen, setIsScheduleModalOpen] = useState(false)
  const [isPropertyModalOpen, setIsPropertyModalOpen] = useState(false)
  const [isRemarkModalOpen, setIsRemarkModalOpen] = useState(false)

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

  useEffect(() => {
    // Todo : 스케쥴 재검색 해야함.
  }, [isScheduleModalOpen])

  useEffect(() => {
    // Todo : 보여줄 목록 재검색 해야함.
  }, [isPropertyModalOpen])

  useEffect(() => {
    // Todo : 특이사항 재검색 해야함.
  }, [isRemarkModalOpen])

  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

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

  const handleDeleteScheduleRows = () => {
    const newShowingRows = scheduleRows.filter(
      (row) => !selectedScheduleRowIds.includes(row.id)
    )
    setScheduleRows(newShowingRows)
    setSelectedScheduleRowIds([])
  }

  const handleDeletePropertyRows = () => {
    const newShowingRows = propertyRows.filter(
      (row) => !selectedPropertyRowIds.includes(row.id)
    )
    setPropertyRows(newShowingRows)
    setSelectedPropertyRowIds([])
  }

  const handleDeleteRemarkRows = () => {
    const newShowingRows = remarkRows.filter(
      (row) => !selectedRemarkRowIds.includes(row.id)
    )
    setRemarkRows(newShowingRows)
    setSelectedRemarkRowIds([])
  }

  const handleSelectClientRow = async (params) => {
    setSelectClientId(params.id)
  }

  const handleScheduleRows = (newSelection) => {
    setSelectedScheduleRowIds(newSelection)
  }

  const handlePropertyRows = (newSelection) => {
    setSelectedPropertyRowIds(newSelection)
  }

  const handleRemarkRows = (newSelection) => {
    setSelectedRemarkRowIds(newSelection)
  }

  const handleCloseModal = () => {
    isScheduleModalOpen ? setIsScheduleModalOpen(false) : null
    isPropertyModalOpen ? setIsPropertyModalOpen(false) : null
    isRemarkModalOpen ? setIsRemarkModalOpen(false) : null
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SingleToolbar text={'고객 상세'} />
        <Stack spacing={4}>
          <Divider></Divider>{' '}
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
                    height={'62.5vh'}
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
                      readOnly={true}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
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
                      readOnly={true}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
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
                      readOnly={true}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
                        },
                      }}
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
                      readOnly={true}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                </Grid>
                <Grid sx={{ borderTop: '2px solid Black' }} />
                <Grid container>
                  <Grid item xs={11}>
                    <CustomDataGrid
                      rows={scheduleRows}
                      columns={scheduleColumns}
                      height={'19vh'}
                      columnVisibilityModel={{
                        scheduleId: false,
                      }}
                      checkboxSelection={true}
                      onRowSelectionModelChange={handleScheduleRows}
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
                    <Stack direction="column" spacing={2}>
                      <IconButton
                        onClick={() => {
                          setIsScheduleModalOpen(!isScheduleModalOpen)
                        }}
                        size="large"
                      >
                        <AddIcon fontSize="large" />
                      </IconButton>
                      <IconButton
                        onClick={handleDeleteScheduleRows}
                        size="large"
                      >
                        <RemoveIcon fontSize="large" />
                      </IconButton>
                    </Stack>
                  </Grid>
                </Grid>
                <Grid sx={{ borderTop: '2px solid Black' }} />
                <Grid container>
                  <Grid item xs={11}>
                    <CustomDataGrid
                      rows={propertyRows}
                      columns={propertyColumns}
                      height={'19vh'}
                      columnVisibilityModel={{
                        propertyId: false,
                      }}
                      checkboxSelection={true}
                      onRowSelectionModelChange={handlePropertyRows}
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
                    <Stack direction="column" spacing={2}>
                      <IconButton
                        onClick={() => {
                          setIsPropertyModalOpen(!isPropertyModalOpen)
                        }}
                        size="large"
                      >
                        <AddIcon fontSize="large" />
                      </IconButton>
                      <IconButton
                        onClick={handleDeletePropertyRows}
                        size="large"
                      >
                        <RemoveIcon fontSize="large" />
                      </IconButton>
                    </Stack>
                  </Grid>
                </Grid>
                <Grid sx={{ borderTop: '2px solid Black' }} />
                <Grid container>
                  <Grid item xs={11}>
                    <CustomDataGrid
                      rows={remarkRows}
                      columns={remarkColumns}
                      height={'19vh'}
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
                    <Stack direction="column" spacing={2}>
                      <IconButton
                        onClick={() => {
                          setIsRemarkModalOpen(!isRemarkModalOpen)
                        }}
                        size="large"
                      >
                        <AddIcon fontSize="large" />
                      </IconButton>
                      <IconButton onClick={handleDeleteRemarkRows} size="large">
                        <RemoveIcon fontSize="large" />
                      </IconButton>
                    </Stack>
                  </Grid>
                </Grid>
              </Stack>
            </Grid>
          </Grid>
        </Stack>
      </Stack>
      <AddScheduleModal
        open={isScheduleModalOpen}
        handleClose={handleCloseModal}
        data={clientId}
      />
      <AddPropertyModal
        open={isPropertyModalOpen}
        handleClose={handleCloseModal}
        data={clientId}
      />
      <AddRemarkModal
        open={isRemarkModalOpen}
        handleClose={handleCloseModal}
        data={{ clientId: clientId, managerId: searchData.managerId }}
      />
    </Box>
  )
}

export default DetailClientPage
