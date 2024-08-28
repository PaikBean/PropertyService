import { useEffect, useState } from 'react'

// Materials
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Grid,
  Modal,
  Stack,
} from '@mui/material'

// Custom Components
import InputName2 from '../textfield/InputName2'
import PropertyType from '../autocomplete/PropertyType'
import TransactionType from '../autocomplete/TransactionType'
import TrasactionTypePriceForm from '@/components/form/TransactionTypePriceForm'
import CommisionFeeForm from '../form/CommisionFeeForm'
import RemarkTextField from '../textfield/RemarkTextField'
import SaveDeleteTogleToolbar from '../toolbar/SaveDeleteTogleToolbar'
import { fetchSearchProperty } from '@/pages/salesLedger/api/fetchSearchProperty'
import { fetchUpdateProperty } from '@/pages/salesLedger/api/fetchUpdateProperty'
import { fetchDeleteProperty } from '@/pages/salesLedger/api/fetchDeleteProperty'

// Utils

const DetailPropertyModal = ({ open, handleClose, data, onClick }) => {
  const initPropertyData = {
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
      shortTermDeposit: null,
      shortTermMonthlyFee: null,
    },
    commision: '',
    remark: '',
  }

  const [mode, setMode] = useState(false)
  const [propertyData, setPropertyData] = useState(initPropertyData)

  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false)

  const handleInputChange = (field, value) => {
    if (field === 'transactionType') {
      setPropertyData((prev) => ({
        ...prev,
        transaction: {
          transactionType: value,
          deposit: null,
          monthlyFee: null,
          jeonseFee: null,
          tradeFee: null,
          shortTermDeposit: null,
          shortTermMonthlyFee: null,
        },
      }))
    } else if (field in propertyData.transaction) {
      setPropertyData((prev) => ({
        ...prev,
        transaction: {
          ...prev.transaction,
          [field]: value,
        },
      }))
    } else {
      setPropertyData((prev) => ({
        ...prev,
        [field]: value,
      }))
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchSearchProperty(data.propertyId);
        if (response.responseCode === "SUCCESS") {
          const property = response.data;
          setPropertyData({
            buildingId: property.buildingId,
            unitNumber: property.unitNumber,
            picManagerId: property.picManagerId,
            propertyTypeId: property.propertyType?.propertyTypeId || null,
            transaction: {
              transactionType: property.transactionType,
              deposit: property.deposit,
              monthlyFee: property.monthlyFee,
              jeonseFee: property.jeonseFee,
              tradeFee: property.tradeFee,
              shortTermDeposit: property.shortTermDeposit,
              shortTermMonthlyFee: property.shortTermMonthlyFee,
            },
            commision: property.commision,
            remark: property.remark,
          });
        }
      } catch (error) {
        console.error(error);
        alert(error);
      }
    };
    fetchData();
  }, [data.propertyId]);

  const handleSave = async () => {
    console.log({...propertyData, propertyId: data.propertyId})
    try {
      const response = await fetchUpdateProperty({...propertyData, propertyId: data.propertyId});
      console.log(response.data)
      if(response.responseCode == "SUCCESS"){
        setPropertyData(initPropertyData)
      } else{
        throw new Error(response.message)
      }
      handleClose()
    } catch (error) {
      console.error(error);
      alert(error)
    }
  }

  const handleDelete = () => {
    setIsDeleteDialogOpen(true)
  }

  const confirmDelete = async () => {
    // 실제 삭제 작업을 여기서 수행합니다.
    try {
      const response = await fetchDeleteProperty(data.propertyId);
      console.log(response.data)
      if(response.responseCode == "SUCCESS"){
        setPropertyData(initPropertyData)
        setIsDeleteDialogOpen(false)
        handleClose()
      } else{
        throw new Error(response.message)
      }
      handleClose()
    } catch (error) {
      console.error(error);
      alert(error)
    }
  }

  return (
    <>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="data-modal-title"
        aria-describedby="data-modal-content"
      >
        <Stack
          spacing={3}
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 1200,
            bgcolor: 'background.paper',
            boxShadow: 24,
            p: 5,
          }}
        >
          <SaveDeleteTogleToolbar
            text={'매물 상세&수정'}
            onSave={handleSave}
            onDelete={handleDelete}
            onChange={() => setMode(!mode)}
            mode={!mode}
          />
          <Stack spacing={2}>
            <Grid container gap={5}>
              <Grid item xs={4}>
                <InputName2
                  label="상세 주소"
                  value={propertyData.unitNumber}
                  onChange={(e) => {
                    handleInputChange('unitNumber', e.target.value)
                  }}
                  name="unitNumber"
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
                <PropertyType
                  value={propertyData.propertyTypeId} 
                  onChange={(value) => {
                    handleInputChange('propertyTypeId', value)
                  }}
                  label="주용도"
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
                <TransactionType
                  value={propertyData.transaction.transactionType} 
                  onChange={(value) => {
                    handleInputChange('transactionType', value)
                  }}
                  label="거래상태"
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
            <Grid container>
              <Grid container sx={{ width: '100%' }}>
                <Grid item xs={6}>
                  <TrasactionTypePriceForm
                    value={propertyData.transaction}
                    onChange={handleInputChange}
                    readOnly={!mode}
                  />
                </Grid>
                <Grid item xs={6}>
                  <CommisionFeeForm
                    value={propertyData.commision}
                    onChange={(e) => {
                      handleInputChange('commision', e.target.value)
                    }}
                    readOnly={!mode}
                  />
                </Grid>
              </Grid>
            </Grid>
            <Grid container gap={4} sx={{ width: '70%' }}>
              <Grid item xs>
                <RemarkTextField
                  value={propertyData.remark}
                  onChange={(e) => {
                    handleInputChange('remark', e.target.value)
                  }}
                  width={1100}
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
          </Stack>
        </Stack>
      </Modal>
      <Dialog
        open={isDeleteDialogOpen}
        onClose={() => setIsDeleteDialogOpen(false)}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{'삭제 확인'}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            이 매물을 정말로 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setIsDeleteDialogOpen(false)}>취소</Button>
          <Button onClick={confirmDelete} color="primary" autoFocus>
            삭제
          </Button>
        </DialogActions>
      </Dialog>
    </>
  )
}

DetailPropertyModal.displayName = 'DetailPropertyModal'

export default DetailPropertyModal