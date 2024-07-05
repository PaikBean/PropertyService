import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import SearchBtn from '@/components/button/SearchBtn'
import InputName2 from '@/components/textfield/InputName2'
import SaveToolbar from '@/components/toolbar/SaveToolbar'
import { Box, Grid, Stack } from '@mui/material'
import { useEffect, useState } from 'react'
import BuildingColumns from './columns/BuilidngColumns'
import { fetchSearchBuildngs } from './api/fetchSearchBuildings'
import CustomDataGrid2 from '@/components/datagrid/CustomDataGrid2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import CommisionFeeForm from '@/components/form/CommisionFeeForm'
import TrasactionTypePriceForm from '@/components/form/TransactionTypePriceForm'
import RemarkTextField from '@/components/textfield/RemarkTextField'
import ManagerAutocomplete from '@/components/autocomplete/ManagerAutocomplete'

const RegistPropertyPage = () => {
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
  const initialData = {
    buildingId: null,
    unitNumber: '',
    picManagerId: null,
    propertyTypeId: null,
    transaction: {
      transactionType: null,
      deposit: null,
      monthlyFee: null,
      jeonseFee: null,
      tradeFee: null,
      shortemDeposit: null,
      shortemMonthlyFee: null,
    },
    commision: '',
    remark: '',
  }

  const [buildingId, setBuildingId] = useState(null)
  const [buildingInfo, setBuildingInfo] = useState(initialBuildingData)
  const [registData, setRegistData] = useState(initialData)

  const [searchCondition, setSearchCondition] = useState(initialSearchCondition)
  const [buildingRows, setBuildingRows] = useState([])

  const handleSelectBuildingRow = async (params) => {
    setBuildingId(params[0])
  }

  const handleSearchInputChange = (field, value) => {
    setSearchCondition((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleInputChange = (field, value) => {
    if (field === 'transactionType') {
      setRegistData((prev) => ({
        ...prev,
        transaction: {
          transactionType: value,
          deposit: null,
          monthlyFee: null,
          jeonseFee: null,
          tradeFee: null,
          shortemDeposit: null,
          shortemMonthlyFee: null,
        },
      }))
    } else if (field in registData.transaction) {
      setRegistData((prev) => ({
        ...prev,
        transaction: {
          ...prev.transaction,
          [field]: value,
        },
      }))
    } else {
      setRegistData((prev) => ({
        ...prev,
        [field]: value,
      }))
    }
  }

  useEffect(() => {
    if (buildingId) {
      const selectedBuilding = buildingRows.find(
        (building) => building.buildingId === buildingId
      )
      if (selectedBuilding) {
        setBuildingInfo(selectedBuilding)
        setRegistData((prev) => ({
          ...prev,
          buildingId: selectedBuilding.buildingId,
        }))
        console.log(selectedBuilding)
      }
    }
  }, [buildingId, buildingRows])

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

  const handleSave = () => {}

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={4}>
        <SaveToolbar
          text={'매물 등록'}
          onChange={() => {
            setMode(!mode)
          }}
          onClick={handleSave}
        />
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
            <Stack spacing={3}>
              <Grid container gap={5}>
                <Grid item xs={2}>
                  <InputName2
                    label="임대인"
                    value={buildingInfo.ownerName}
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
                    value={buildingInfo.ownerPhonNumber}
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
                <Grid item xs={2}>
                  <ManagerAutocomplete
                    value={registData.picManagerId} // Add this line
                    onChange={(value) => {
                      handleInputChange('picManagerId', value)
                    }}
                    label="담당 매니저"
                  />
                </Grid>
                <Grid item xs={2}>
                  <div>상업용/주거용</div>
                </Grid>
              </Grid>
              <Grid container gap={5}>
                <Grid item xs={7}>
                  <InputName2
                    label="주소"
                    value={buildingInfo.buildingAddress}
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
                <Grid item xs={4}>
                  <InputName2
                    label="상세 주소"
                    value={registData.unitNumber}
                    onChange={(e) => {
                      handleInputChange('unitNumber', e.target.value)
                    }}
                    name="unitNumber"
                  />
                </Grid>
              </Grid>
              <Grid container sx={{ width: '100%' }}>
                <Grid item xs={6}>
                  <TrasactionTypePriceForm
                    value={registData.transaction}
                    onChange={handleInputChange}
                  />
                </Grid>
                <Grid item xs={5.5}>
                  <CommisionFeeForm
                    value={registData.commision}
                    onChange={(e) => {
                      handleInputChange('commision', e.target.value)
                    }}
                  ></CommisionFeeForm>
                </Grid>
              </Grid>
              <Grid container gap={4} sx={{ width: '70%' }}>
                <Grid item xs>
                  <RemarkTextField
                    value={registData.remark}
                    onChange={(e) => {
                      handleInputChange('remark', e.target.value)
                    }}
                    width={1100}
                  />
                </Grid>
              </Grid>
            </Stack>
          </Grid>
        </Grid>
      </Stack>
    </Box>
  )
}

export default RegistPropertyPage
