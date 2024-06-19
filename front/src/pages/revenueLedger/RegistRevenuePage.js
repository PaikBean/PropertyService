import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import ManagerAutocomplete from '@/components/autocomplete/ManagerAutocomplete'
import InputADdressL3 from '@/components/textfield/InputAddressL3'
import InputName2 from '@/components/textfield/InputName2'
import SaveToolbar from '@/components/toolbar/SaveToolbar'
import { Alert, Box, Grid, Snackbar, Stack } from '@mui/material'
import { useState } from 'react'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import BasicDatePicker from '@/components/datepicker/BasicDatePicker'
import dayjs from 'dayjs'
import RemarkTextField from '@/components/textfield/RemarkTextField'
import CommisionFeeForm from '@/components/form/CommisionFeeForm'
import TrasactionTypePriceForm from '@/components/form/TransactionTypePriceForm'
import { fetchRegistRevenue } from './api/fetchRegistRevenue'

const RegistRevenuePage = ({ initMainPage }) => {
  const initialData = {
    managerId: null,
    ownerName: '',
    clientName: '',
    addressL1: null,
    addressL2: null,
    addressL3: '',
    startDate: '',
    endDate: '',
    transaction: {
      transactionType: null,
      deposit: null,
      monthlyFee: null,
      jeonseFee: null,
      tradeFee: null,
      shortemDeposit: null,
      shortemMonthlyFee: null,
    },
    commision: null,
    remark: '',
  }

  const [registData, setRegistData] = useState(initialData)

  const [openSuccessSnackbar, setOpenSuccessSnackbar] = useState(false)
  const [openErrorSnackbar, setOpenErrorSnackbar] = useState(false)
  const [errorMessage, setErrorMessage] = useState('')

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

  const handleSave = async () => {
    try {
      const result = await fetchRegistRevenue(registData)
      setOpenSuccessSnackbar(true)
      setRegistData(initialData)
    } catch (error) {
      setErrorMessage(error.message)
      setOpenErrorSnackbar(true)
    }
    return
  }

  const handleSuccessSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    openSuccessSnackbar ? setOpenSuccessSnackbar(false) : null
  }

  const handleErrorSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    openErrorSnackbar ? setOpenErrorSnackbar(false) : null
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={5}>
        <SaveToolbar text="매출 장부 등록" onClick={handleSave}></SaveToolbar>
        <Stack gap={3}>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <Grid item xs={2.5}>
              <ManagerAutocomplete
                value={registData.managerId} // Add this line
                onChange={(value) => {
                  handleInputChange('managerId', value)
                }}
              />
            </Grid>
            <Grid item xs={2.5}>
              <InputName2
                label="임대인"
                value={registData.ownerName}
                onChange={(e) => {
                  handleInputChange('ownerName', e.target.value)
                }}
              />
            </Grid>
            <Grid item xs={2.5}>
              <InputName2
                label="임차인"
                value={registData.clientName}
                onChange={(e) => {
                  handleInputChange('clientName', e.target.value)
                }}
              />
            </Grid>
          </Grid>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <Grid item xs={2.5}>
              <AddressL1
                value={registData.addressL1} // Add this line
                onChange={(value) => {
                  handleInputChange('addressL1', value)
                }}
              />
            </Grid>
            <Grid item xs={3}>
              <AddressL2
                value={registData.addressL2} // Add this line
                addressLevel1={registData.addressL1}
                onChange={(value) => {
                  handleInputChange('addressL2', value)
                }}
              />
            </Grid>
            <Grid item xs={5}>
              <InputADdressL3
                value={registData.addressL3}
                onChange={(e) => {
                  handleInputChange('addressL3', e.target.value)
                }}
              />
            </Grid>
          </Grid>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <BasicDatePicker
                label="계약 시작일"
                value={dayjs(registData.startDate)}
                onChange={(value) => {
                  handleInputChange('startDate', value.format('YYYYMMDD'))
                }}
              />
              <BasicDatePicker
                label="계약 종료일"
                value={dayjs(registData.endDate)}
                onChange={(value) => {
                  handleInputChange('endDate', value.format('YYYYMMDD'))
                }}
              />
            </LocalizationProvider>
          </Grid>
          <Grid container gap={5} sx={{ width: '80%' }}>
            <Grid item xs={5}>
              <TrasactionTypePriceForm
                value={registData.transaction}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={5}>
              <CommisionFeeForm
                value={registData.commision}
                onChange={(e) => {
                  handleInputChange('commision', e.target.value)
                }}
              ></CommisionFeeForm>
            </Grid>
          </Grid>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <Grid item xs>
              <RemarkTextField
                value={registData.remark}
                onChange={(e) => {
                  handleInputChange('remark', e.target.value)
                }}
              />
            </Grid>
          </Grid>
        </Stack>
      </Stack>
      <Snackbar
        open={openSuccessSnackbar}
        autoHideDuration={5000}
        onClose={handleSuccessSnackbarClose}
      >
        <Alert
          onClose={handleSuccessSnackbarClose}
          severity="success"
          variant="filled"
          sx={{ width: '100%' }}
        >
          매출 저장 완료
        </Alert>
      </Snackbar>
      <Snackbar
        open={openErrorSnackbar}
        autoHideDuration={5000}
        onClose={handleErrorSnackbarClose}
      >
        <Alert
          onClose={handleErrorSnackbarClose}
          severity="error"
          variant="filled"
          sx={{ width: '100%' }}
        >
          {errorMessage}
        </Alert>
      </Snackbar>
    </Box>
  )
}

RegistRevenuePage.displayName = 'RegistRevenuePage'

export default RegistRevenuePage
