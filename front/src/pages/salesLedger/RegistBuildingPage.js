import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import InputADdressL3 from '@/components/textfield/InputAddressL3'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import RemarkTextField from '@/components/textfield/RemarkTextField'
import SaveToolbar from '@/components/toolbar/SaveToolbar'
import { Alert, Box, Divider, Grid, Snackbar, Stack } from '@mui/material'
import { useState } from 'react'
import { fetchRegistBuilding } from './api/fetchRegistBuilding'

const ResgistBuildingPage = () => {
  const initialData = {
    ownerName: '',
    ownerRelation: '',
    ownerPhoneNumber: '',
    buildingAddressLevel1: null,
    buildingAddressLevel2: null,
    buildingAddressLevel3: '',
    buildingRemark: '',
  }

  const [registData, setRegistData] = useState(initialData)

  const [openSuccessSnackbar, setOpenSuccessSnackbar] = useState(false)
  const [openErrorSnackbar, setOpenErrorSnackbar] = useState(false)
  const [errorMessage, setErrorMessage] = useState('')

  const handleInputChange = (field, value) => {
    setRegistData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSave = async () => {
    try {
      const result = await fetchRegistBuilding(registData)
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
      <Stack spacing={2}>
        <SaveToolbar text="건물 등록" onClick={handleSave}></SaveToolbar>
        <Divider></Divider>
        <Stack spacing={3}>
          <Divider sx={{ width: '100%', borderColor: 'white' }}></Divider>
          <Grid container gap={5} sx={{ width: '70%' }}>
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
                label="관계"
                value={registData.ownerRelation}
                onChange={(e) => {
                  handleInputChange('ownerRelation', e.target.value)
                }}
              />
            </Grid>
            <Grid item xs={2.5}>
              <InputPhoneNumber
                value={registData.ownerPhoneNumber}
                onChange={(formattedPhoneNumber) =>
                  handleInputChange('ownerPhoneNumber', formattedPhoneNumber)
                }
                name="ownerPhoneNumber"
              />
            </Grid>
          </Grid>
          <Grid container gap={5} sx={{ width: '80%' }}>
            <Grid item xs={2.5}>
              <AddressL1
                value={registData.buildingAddressLevel1} // Add this line
                onChange={(value) => {
                  handleInputChange('buildingAddressLevel1', value)
                }}
              />
            </Grid>
            <Grid item xs={3}>
              <AddressL2
                value={registData.buildingAddressLevel2} // Add this line
                addressLevel1={registData.buildingAddressLevel1}
                onChange={(value) => {
                  handleInputChange('buildingAddressLevel2', value)
                }}
              />
            </Grid>
            <Grid item xs={4}>
              <InputADdressL3
                value={registData.buildingAddressLevel3}
                onChange={(e) => {
                  handleInputChange('buildingAddressLevel3', e.target.value)
                }}
              />
            </Grid>
          </Grid>
          <Grid container gap={5} sx={{ width: '70%' }}>
            <Grid item xs>
              <RemarkTextField
                value={registData.buildingRemark}
                onChange={(e) => {
                  handleInputChange('buildingRemark', e.target.value)
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
          빌딩 등록 완료
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

ResgistBuildingPage.displayName = 'ResgistBuildingPage'

export default ResgistBuildingPage
