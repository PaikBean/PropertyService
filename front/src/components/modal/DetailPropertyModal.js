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
import SaveTogleToolbar from '../toolbar/SaveTogleToolbar'
import { useState } from 'react'
import InputName2 from '../textfield/InputName2'
import PropertyType from '../autocomplete/PropertyType'
import TransactionType from '../autocomplete/TransactionType'
import TrasactionTypePriceForm from '@/components/form/TransactionTypePriceForm'
import CommisionFeeForm from '../form/CommisionFeeForm'
import RemarkTextField from '../textfield/RemarkTextField'
import SaveDeleteTogleToolbar from '../toolbar/SaveDeleteTogleToolbar'

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
      shortemDeposit: null,
      shortemMonthlyFee: null,
    },
    commision: '',
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
          shortemDeposit: null,
          shortemMonthlyFee: null,
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

  const handleSave = () => {}

  const handleDelete = () => {
    setIsDeleteDialogOpen(true)
  }

  const confirmDelete = () => {
    // 실제 삭제 작업을 여기서 수행합니다.
    setIsDeleteDialogOpen(false)
    handleClose()
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
                  value={propertyData.propertyTypeId} // Add this line
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
                  value={propertyData.transactionTypeId} // Add this line
                  onChange={(value) => {
                    handleInputChange('transactionTypeId', value)
                  }}
                  label="거래상태"
                  readOnly={!mode}
                  sx={{
                    '& .MuiInputBase-root': {
                      backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                      cursor: !mode ? 'not-allowed' : '', // 커서 변경
                    },
                  }}
                ></TransactionType>
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
                  ></CommisionFeeForm>
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
