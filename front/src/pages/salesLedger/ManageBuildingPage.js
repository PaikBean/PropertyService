import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid2 from '@/components/datagrid/CustomDataGrid2'
import InputName2 from '@/components/textfield/InputName2'
import SaveTogleToolbar from '@/components/toolbar/SaveTogleToolbar'
import { Box, Divider, Grid, IconButton, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import BuildingColumns from './columns/BuilidngColumns'
import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import InputADdressL3 from '@/components/textfield/InputAddressL3'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import BuildingRemarkColumns from './columns/BuildingRemarkColumns'
import { fetchSEarchBuildingList } from './api/fetchSearchBuildingList'

const ManageBuildingPage = () => {
  const initialSearchCondition = {
    ownerPhoneNumber: '',
    addressLevel1Id: null,
    addressLevel2Id: null,
  }
  const initialBuildingData = {
    ownerName: '',
    ownerRelation: '',
    ownerPhoneNumber: '',
    buildingAddressLevel1: null,
    buildingAddressLevel2: null,
    buildingAddressLevel3: '',
    buildingRemark: '',
  }

  const [mode, setMode] = useState(false)

  const [buildingId, setBuildingId] = useState(null)

  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)
  const [buildingData, setBuildingData] = useState(initialBuildingData)

  const [buildingRows, setBuildingRows] = useState([])
  const [buildingRemarkRows, setBuildingRemarkRows] = useState([])

  const [selectedBuildingRemarkRowIds, setSelectedBuildingRemarkRowIds] =
    useState([])

  const [isBuildingRemarkModalOpen, setIsBuildingRemarkModalOpen] =
    useState(false)

  useEffect(() => {
    // if (buildingId) {
    //   const selectedBuilding = buildingRows.find(
    //     (building) => building.buildingId === buildingId
    //   )
    //   if (selectedBuilding) {
    //     setBuildingInfo(selectedBuilding)
    //     setRegistData((prev) => ({
    //       ...prev,
    //       buildingId: selectedBuilding.buildingId,
    //     }))
    //     console.log(selectedBuilding)
    //   }
    // }
    // alert('빌딩 선택 : ', buildingRows)
  }, [buildingId, buildingRows])

  const handleRemarkRows = () => {}

  const handleDeleteRemarkRows = () => {}

  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleInputChange = (field, value) => {
    setBuildingData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearch = async () => {
    try {
      // const response = await fetchSearchBuildng(searchCondition)
      // if (response.responseCode === 'SUCCESS') {
      //   setBuildingRows(response.data)
      // } else {
      //   console.error('Failed to fetch building list:', response.message)
      // }
      console.log(searchCondition)
      const response = await fetchSEarchBuildingList(searchCondition)
      console.log(response)
    } catch (error) {
      console.error('Error fetching building list:', error)
    }
  }

  const handleSelectBuildingRow = async (params) => {
    setBuildingId(params[0])
  }

  const handleSave = () => {}

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SaveTogleToolbar
          text={'건물 관리'}
          onChange={() => {
            setMode(!mode)
          }}
          onClick={handleSave}
        ></SaveTogleToolbar>
        <Stack spacing={4}>
          <Divider></Divider>{' '}
          <Grid
            container
            gap={5}
            sx={{
              '& .revenu-header-css': {
                backgroundColor: 'lightgrey',
              },
            }}
          >
            <Grid item xs={3}>
              <Stack spacing={1}>
                <Grid container gap={1}>
                  <Grid item xs={4.5}>
                    <AddressL1
                      value={searchCondition.addressLevel1Id} // Add this line
                      onChange={(value) => {
                        handleSearchInputChange('addressLevel1Id', value)
                      }}
                      sx={{
                        '.MuiInputBase-input': { height: '10px' },
                        '.MuiInputLabel-root': {
                          top: '-6px',
                        },
                      }}
                      label="시/도"
                    />
                  </Grid>
                  <Grid item xs={7}>
                    <AddressL2
                      value={searchCondition.addressLevel2Id} // Add this line
                      addressLevel1={searchCondition.addressLevel1Id}
                      onChange={(value) => {
                        handleSearchInputChange('addressLevel2Id', value)
                      }}
                      sx={{
                        '.MuiInputBase-input': { height: '10px' },
                        '.MuiInputLabel-root': {
                          top: '-6px',
                        },
                      }}
                      label="시/구/동"
                    />
                  </Grid>
                </Grid>
                <Grid container gap={1}>
                  <Grid item xs={10}>
                    <InputName2
                      label="임대인 전화번호"
                      value={searchCondition.ownerPhoneNumber}
                      onChange={(e) => {
                        handleSearchInputChange(
                          'ownerPhoneNumber',
                          e.target.value
                        )
                      }}
                      sx={{
                        '.MuiInputBase-input': { height: '10px' },
                        '.MuiInputLabel-root': {
                          top: '-6px',
                        },
                      }}
                      name="ownerPhoneNumber"
                    />
                  </Grid>
                  <Grid item xs={1}>
                    <SearchBtn onClick={handleSearch} />
                  </Grid>
                </Grid>
                <Grid item>
                  <CustomDataGrid2
                    rows={buildingRows}
                    columns={BuildingColumns}
                    height={'64vh'}
                    columnVisibilityModel={{
                      buildingId: false,
                      ownerRelation: false,
                      ownerPhonNumber: false,
                    }}
                    showAll={true}
                    onRowSelectionModelChange={handleSelectBuildingRow}
                    pageSize={10}
                    rowHeight={48}
                    getRowId={(row) => row.buildingId} // getRowId 속성 전달
                  />
                </Grid>
              </Stack>
            </Grid>
            <Grid item xs={8.5}>
              <Stack spacing={5}>
                <Grid container gap={5} sx={{ width: '70%' }}>
                  <Grid item xs={2.5}>
                    <InputName2
                      label="임대인"
                      value={buildingData.ownerName}
                      onChange={(e) => {
                        handleInputChange('ownerName', e.target.value)
                      }}
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={2.5}>
                    <InputName2
                      label="관계"
                      value={buildingData.ownerRelation}
                      onChange={(e) => {
                        handleInputChange('ownerRelation', e.target.value)
                      }}
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={5}>
                    <InputPhoneNumber
                      value={buildingData.ownerPhoneNumber}
                      onChange={(formattedPhoneNumber) =>
                        handleInputChange(
                          'ownerPhoneNumber',
                          formattedPhoneNumber
                        )
                      }
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                      name="ownerPhoneNumber"
                      label="임대인 전화번호"
                    />
                  </Grid>
                </Grid>
                <Grid container gap={5} sx={{ width: '80%' }}>
                  <Grid item xs={2.5}>
                    <AddressL1
                      value={buildingData.buildingAddressLevel1} // Add this line
                      onChange={(value) => {
                        handleInputChange('buildingAddressLevel1', value)
                      }}
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                      label="시/도"
                    />
                  </Grid>
                  <Grid item xs={3}>
                    <AddressL2
                      value={buildingData.buildingAddressLevel2} // Add this line
                      addressLevel1={buildingData.buildingAddressLevel1}
                      onChange={(value) => {
                        handleInputChange('buildingAddressLevel2', value)
                      }}
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                      label="시/구/동"
                    />
                  </Grid>
                  <Grid item xs={4}>
                    <InputADdressL3
                      value={buildingData.buildingAddressLevel3}
                      onChange={(e) => {
                        handleInputChange(
                          'buildingAddressLevel3',
                          e.target.value
                        )
                      }}
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                      label="상세 주소"
                    />
                  </Grid>
                </Grid>
                <Grid container gap={5} sx={{ width: '100%' }}>
                  <Grid container>
                    <Grid item xs={11}>
                      <CustomDataGrid2
                        rows={buildingRemarkRows}
                        columns={BuildingRemarkColumns}
                        height={'53.5vh'}
                        columnVisibilityModel={{
                          buildingRemarkId: false,
                        }}
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
                </Grid>
              </Stack>
            </Grid>
          </Grid>
        </Stack>
      </Stack>
    </Box>
  )
}

ManageBuildingPage.displayName = 'ManageBuildingPage'

export default ManageBuildingPage
