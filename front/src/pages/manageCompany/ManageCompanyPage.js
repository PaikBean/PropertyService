import CustomDataGrid2 from '@/components/datagrid/CustomDataGrid2'
import SaveTogleToolbar from '@/components/toolbar/SaveTogleToolbar'
import { Box, Button, Grid, IconButton, Stack, Typography } from '@mui/material'
import { useEffect, useState } from 'react'
import DepartmentColumn from './columns/DepartmentColumn'
import InputName2 from '@/components/textfield/InputName2'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import BasicDatePicker from '@/components/datepicker/BasicDatePicker'
import dayjs from 'dayjs'
import ManagerColumns from './columns/ManagerColumns'
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts'

const ManageCompanyPage = () => {
  const initDepartmentInfo = {
    departmentName: '',
    departmentCode: '',
    departmentPresidentId: '',
    departmentPresidentName: '',
  }

  const initSearchDate = {
    startDate: '',
    endDate: '',
  }

  const [mode, setMode] = useState(false)
  const [searchDate, setSearchDate] = useState(initSearchDate)

  const [departmentId, setDepartmentId] = useState(null)

  const [departmentInfo, setDepartmentInfo] = useState(initDepartmentInfo)

  const [departmentRows, setDepartmentRows] = useState([])
  const [managerRows, setManagerRows] = useState([])

  const handleSave = () => {}

  const handleAddDepartment = () => {}

  const handleSelectDepartmentRow = () => {}

  const handleSearchInputChange = () => {}

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={4}>
        <SaveTogleToolbar
          text={'조직 관리'}
          onChange={() => {
            setMode(!mode)
          }}
          onClick={handleSave}
        ></SaveTogleToolbar>
        <Grid
          container
          gap={5}
          pl={4}
          sx={{
            '& .revenu-header-css': {
              backgroundColor: 'lightgrey',
            },
          }}
        >
          <Grid item xs={3.5}>
            <Stack spacing={2}>
              <Grid container gap={5} justifyContent="end">
                <Grid item>
                  <Button
                    variant="contained"
                    onClick={handleAddDepartment}
                    sx={{
                      height: 35,
                      marginBottom: '1.5px',
                      backgroundColor: '#56866fec',
                      '&:hover': {
                        backgroundColor: '#56866f',
                      },
                    }}
                  >
                    부서 등록
                  </Button>
                </Grid>
              </Grid>
              <Grid item>
                <CustomDataGrid2
                  rows={departmentRows}
                  columns={DepartmentColumn}
                  height={'70vh'}
                  columnVisibilityModel={{
                    departmentId: false,
                  }}
                  showAll={true}
                  onRowSelectionModelChange={handleSelectDepartmentRow}
                  pageSize={10}
                  rowHeight={48}
                  getRowId={(row) => row.buildingId} // getRowId 속성 전달
                />
              </Grid>
            </Stack>
          </Grid>
          <Grid item xs={8}>
            <Stack spacing={2}>
              <Grid container gap={5}>
                <Grid item xs={3}>
                  <InputName2
                    label="부서명"
                    value={departmentInfo.departmentName}
                    onChange={(e) => {
                      handleInputChange('departmentName', e.target.value)
                    }}
                    name="departmentName"
                    readOnly={true}
                    sx={{
                      '& .MuiInputBase-root': {
                        backgroundColor: !mode ? '#f5f5f5' : 'inherite', // 회색빛 배경 설정
                        cursor: !mode ? 'not-allowed' : 'inherite', // 커서 변경
                      },
                    }}
                  />
                </Grid>
                <Grid item xs={2}>
                  <InputName2
                    label="부서 코드"
                    value={departmentInfo.departmentName}
                    onChange={(e) => {
                      handleInputChange('departmentName', e.target.value)
                    }}
                    name="departmentName"
                    readOnly={true}
                    sx={{
                      '& .MuiInputBase-root': {
                        backgroundColor: !mode ? '#f5f5f5' : 'inherite', // 회색빛 배경 설정
                        cursor: !mode ? 'not-allowed' : 'inherite', // 커서 변경
                      },
                    }}
                  />
                </Grid>
                <Grid item xs={2}>
                  <InputName2
                    label="부서장"
                    value={departmentInfo.departmentName}
                    onChange={(e) => {
                      handleInputChange('departmentName', e.target.value)
                    }}
                    name="departmentName"
                    readOnly={true}
                    sx={{
                      '& .MuiInputBase-root': {
                        backgroundColor: !mode ? '#f5f5f5' : 'inherite', // 회색빛 배경 설정
                        cursor: !mode ? 'not-allowed' : 'inherite', // 커서 변경
                      },
                    }}
                  />
                </Grid>
              </Grid>
              <Grid container gap={5}>
                <Grid container>
                  <Grid item xs={11}>
                    <Stack spacing={2}>
                      <Grid item>
                        <Typography variant="h6">매출액 조회</Typography>
                      </Grid>
                      <Grid container gap={5}>
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                          <Grid item>
                            <BasicDatePicker
                              label="시작일"
                              value={dayjs(searchDate.startDate)}
                              onChange={(value) => {
                                handleSearchInputChange(
                                  'startDate',
                                  value.format('YYYYMMDD')
                                )
                              }}
                            />
                          </Grid>
                          <Grid item>
                            <BasicDatePicker
                              label="종료일"
                              value={dayjs(searchDate.endDate)}
                              onChange={(value) => {
                                handleSearchInputChange(
                                  'endDate',
                                  value.format('YYYYMMDD')
                                )
                              }}
                            />
                          </Grid>
                        </LocalizationProvider>
                        <Grid item alignSelf="flex-end">
                          <Typography fontSize={25}>0</Typography>
                        </Grid>
                        <Grid item alignSelf="flex-end">
                          <Typography> 원</Typography>
                        </Grid>
                      </Grid>
                    </Stack>
                  </Grid>
                  <Grid
                    item
                    xs={1}
                    display="flex"
                    justifyContent="flex-end"
                    alignItems="flex-end"
                  >
                    <IconButton onClick={() => {}} size="large" sx={{ mb: -2 }}>
                      <ManageAccountsIcon fontSize="large" />
                    </IconButton>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item>
                <CustomDataGrid2
                  rows={managerRows}
                  columns={ManagerColumns}
                  height={'54vh'}
                  columnVisibilityModel={{
                    managerId: false,
                  }}
                  showAll={true}
                  pageSize={10}
                  rowHeight={48}
                  getRowId={(row) => row.buildingId} // getRowId 속성 전달
                />
              </Grid>
            </Stack>
          </Grid>
        </Grid>
      </Stack>
    </Box>
  )
}

export default ManageCompanyPage
