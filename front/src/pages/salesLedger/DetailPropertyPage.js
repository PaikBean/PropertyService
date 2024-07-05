import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid2 from '@/components/datagrid/CustomDataGrid2'
import InputName2 from '@/components/textfield/InputName2'
import SingleToolbar from '@/components/toolbar/SingleToolbar'
import { Box, Grid, IconButton, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import BuildingColumns from './columns/BuilidngColumns'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import BuildingRemarkColumns from './columns/BuildingRemarkColumns'
import PropertyListColumns from './columns/PropertyListColumns'

const DetailPropertyPage = () => {
  const initialSearchCondition = {
    ownerPhoneNumber: '',
    addressLevel1Id: null,
    addressLevel2Id: null,
  }
  const initialBuildingData = {
    ownerName: '',
    ownerRelation: '',
    ownerPhonNumber: '',
    buildingAddress: '',
  }

  const [buildingId, setBuildingId] = useState(null)

  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)
  const [buildingData, setBuildingData] = useState(initialBuildingData)

  const [buildingRows, setBuildingRows] = useState([])
  const [buildingRemarkRows, setBuildingRemarkRows] = useState([])
  const [propertyRows, setPropertyRows] = useState([])

  const [selectedBuildingRowIds, setSelectedBuildingRowIds] = useState([])
  const [selectedPropertyRowIds, setSelectedPropertyRowIds] = useState([])
  const [selectedBuildingRemarkRowIds, setSelectedBuildingRemarkRowIds] =
    useState([])

  const [isBuildingRemarkModalOpen, setIsBuildingRemarkModalOpen] =
    useState(false)
  const [isPropertyModalOpen, setIsPropertyModalOpen] = useState(false)

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

  const handleBuildingRemarkRows = () => {}

  const handleDeleteBuildingRemarkRows = () => {}

  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSelectBuildingRow = async (params) => {
    setBuildingId(params[0])
  }

  const handleSearch = async () => {
    try {
      const response = await fetchSearchBuildngs(searchCondition)
      if (response.responseCode === 'SUCCESS') {
        setBuildingRows(response.data)
      } else {
        console.error('Failed to fetch building list:', response.message)
      }
    } catch (error) {
      console.error('Error fetching building list:', error)
    }
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={4}>
        <SingleToolbar text={'매물 상세'} />
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
                  height={'65.5vh'}
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
            <Stack spacing={2}>
              <Grid container gap={5}>
                <Grid item xs={1.5}>
                  <InputName2
                    label="임대인"
                    value={initialBuildingData.ownerName}
                    onChange={(e) => {
                      handleInputChange('ownerName', e.target.value)
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
                    value={initialBuildingData.ownerPhonNumber}
                    onChange={(formattedPhoneNumber) =>
                      handleInputChange('ownerPhonNumber', formattedPhoneNumber)
                    }
                    name="ownerPhonNumber"
                    readOnly={false}
                    sx={{
                      '& .MuiInputBase-root': {
                        backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                        cursor: 'not-allowed', // 커서 변경
                      },
                    }}
                  />
                </Grid>
                <Grid item xs={6.5}>
                  <InputName2
                    label="주소"
                    value={initialBuildingData.buildingAddress}
                    onChange={(e) => {
                      handleInputChange('buildingAddress', e.target.value)
                    }}
                    name="buildingAddress"
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
              <Grid container>
                <Grid item xs={11}>
                  <CustomDataGrid2
                    rows={buildingRemarkRows}
                    columns={BuildingRemarkColumns}
                    height={'20vh'}
                    columnVisibilityModel={{
                      remarkId: false,
                    }}
                    checkboxSelection={true}
                    onRowSelectionModelChange={handleBuildingRemarkRows}
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
                        setIsBuildingRemarkModalOpen(!isBuildingRemarkModalOpen)
                      }}
                      size="large"
                    >
                      <AddIcon fontSize="large" />
                    </IconButton>
                    <IconButton
                      onClick={handleDeleteBuildingRemarkRows}
                      size="large"
                    >
                      <RemoveIcon fontSize="large" />
                    </IconButton>
                  </Stack>
                </Grid>
              </Grid>
              <Grid item sx={{ width: '100%' }}>
                <CustomDataGrid2
                  rows={propertyRows}
                  columns={PropertyListColumns}
                  height={'47vh'}
                  columnVisibilityModel={{
                    remarkId: false,
                  }}
                  onRowSelectionModelChange={handleBuildingRemarkRows}
                  showAll={true}
                  pageSize={10}
                  rowHeight={48}
                />
              </Grid>
            </Stack>
          </Grid>
        </Grid>
      </Stack>
    </Box>
  )
}

DetailPropertyPage.displayName = 'DetailPropertyPage'

export default DetailPropertyPage
