import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import SearchBtn from '@/components/button/SearchBtn'
import CustomDataGrid2 from '@/components/datagrid/CustomDataGrid2'
import InputName2 from '@/components/textfield/InputName2'
import SingleToolbar from '@/components/toolbar/SingleToolbar'
import { Box, Button, Divider, Grid, IconButton, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import BuildingColumns from './columns/BuilidngColumns'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'
import BuildingRemarkColumns from './columns/BuildingRemarkColumns'
import PropertyListColumns from './columns/PropertyListColumns'
import DetailPropertyModal from '@/components/modal/DetailPropertyModal'
import { fetchSEarchBuildingList } from './api/fetchSearchBuildingList'
import { fetchSearchBuildingDetail } from './api/fetchSearchBuildingDetail'
import AddBuildingRemarkModal from '@/components/modal/AddBuildingRemarkModal'
import { fetchDeleteBuildingRemark } from './api/fetchDeleteBuildingRemark'

const DetailPropertyPage = () => {
  const initialSearchCondition = {
    ownerPhoneNumber: '',
    addressLevel1Id: null,
    addressLevel2Id: null,
  }
  const initialBuildingData = {
    ownerName: '',
    ownerRelation: '',
    ownerPhoneNumber: '',
    buildingAddress: '',
  };

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
    const fetchData = async () => {
      try {
        const response = await fetchSearchBuildingDetail(buildingId);
        console.log(response.data)
        setBuildingData(response.data); // 데이터를 상태로 설정
        setBuildingRemarkRows(response.data.buildingRemarkList)
        setPropertyRows(response.data.buildingPropertyList)
      } catch (error) {
        console.error(error);
        alert(error)
      }
    };
    if(buildingId != null)
      fetchData();
  }, [buildingId, buildingRows])

  const handleBuildingRemarkRows = (params) => {
    setSelectedBuildingRemarkRowIds(params[0])
  }

  const handleDeleteBuildingRemarkRows = async () => {
    console.log(selectedBuildingRemarkRowIds)
    try{
      const response = await fetchDeleteBuildingRemark(selectedBuildingRemarkRowIds)
      if(response.responseCode === 'SUCCESS') {
        console.log(response)
        // 특이사항 목록 재검색
        try {
          const response = await fetchSearchBuildingDetail(buildingId);
          console.log(response.data)
          setBuildingData(response.data); // 데이터를 상태로 설정
          setBuildingRemarkRows(response.data.buildingRemarkList)
          setPropertyRows(response.data.buildingPropertyList)
        } catch (error) {
          console.error(error);
        }
      } else{
        throw new Error(response.message || 'Error!')
      }
    } catch(error) {
      alert(error)
    }
  }

  const handleCloseBuildingRemarkModal = async () => {
    try {
      const response = await fetchSearchBuildingDetail(buildingId);
      console.log(response.data)
      setBuildingData(response.data); // 데이터를 상태로 설정
      setBuildingRemarkRows(response.data.buildingRemarkList)
      setPropertyRows(response.data.buildingPropertyList)
    } catch (error) {
      console.error(error);
    }
    isBuildingRemarkModalOpen ? setIsBuildingRemarkModalOpen(false) : null
  }

  const handlePropertyRows = (params) => {
    setSelectedPropertyRowIds(params[0])
    setIsPropertyModalOpen(true)
  }

  const handleCloseModal = () => {
    isPropertyModalOpen ? setIsPropertyModalOpen(false) : null
  }

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
      const response = await fetchSEarchBuildingList(searchCondition)
      setBuildingRows(response.data)
    } catch (error) {
      console.error('Error fetching building list:', error)
    }
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SingleToolbar text={'매물 상세'} />
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
              <Stack spacing={2}>
                <Grid container gap={5}>
                  <Grid item xs={1.5}>
                    <InputName2
                      label="임대인"
                      value={buildingData.ownerName}
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
                      value={buildingData.ownerPhoneNumber}
                      onChange={(formattedPhoneNumber) =>
                        handleInputChange(
                          'ownerPhonNumber',
                          formattedPhoneNumber
                        )
                      }
                      name="ownerPhonNumber"
                      readOnly={false}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
                        },
                      }}
                      label="임대인 전화번호"
                    />
                  </Grid>
                  <Grid item xs={6.5}>
                    <InputName2
                      label="주소"
                      value={buildingData.buildingAddress}
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
                      height={'22.5vh'}
                      columnVisibilityModel={{
                        buildingRemarkId: false,
                      }}
                      checkboxSelection={true}
                      onRowSelectionModelChange={handleBuildingRemarkRows}
                      showAll={true}
                      pageSize={10}
                      rowHeight={48}
                      getRowId={(row) => row.buildingRemarkId} // getRowId 속성 전달
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
                          setIsBuildingRemarkModalOpen(
                            !isBuildingRemarkModalOpen
                          )
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
                    height={'43vh'}
                    columnVisibilityModel={{
                      propertyId: false,
                    }}
                    onRowSelectionModelChange={handlePropertyRows}
                    showAll={true}
                    pageSize={10}
                    rowHeight={48}
                    getRowId={(row) => row.propertyId} // getRowId 속성 전달
                  />
                </Grid>
              </Stack>
            </Grid>
          </Grid>
        </Stack>
      </Stack>
      <DetailPropertyModal
        open={isPropertyModalOpen}
        handleClose={handleCloseModal}
        data={{propertyId: selectedPropertyRowIds}}
      ></DetailPropertyModal>
      <AddBuildingRemarkModal
        open={isBuildingRemarkModalOpen}
        handleClose={handleCloseBuildingRemarkModal}
        data={{buildingId: buildingId}}
      />
    </Box>
  )
}

DetailPropertyPage.displayName = 'DetailPropertyPage'

export default DetailPropertyPage
