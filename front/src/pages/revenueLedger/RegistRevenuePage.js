import AddressL1 from '@/components/autocomplete/AddressL1'
import AddressL2 from '@/components/autocomplete/AddressL2'
import InputADdressL3 from '@/components/textfield/InputAddressL3'
import SaveToolbar from '@/components/toolbar/SaveToolbar'
import { Box, Grid, Stack } from '@mui/material'
import { useState } from 'react'

const RegistRevenuePage = () => {
  const [registData, setRegistData] = useState({
    managerId: null,
    ownerName: '',
    clientName: '',
    addressL1: null,
    addressL2: null,
    addressL3: '',
    startDate: '',
    endDate: '',
    transactionType: null,
    deposit: null,
    monthlyFee: null,
    jeonseFee: null,
    tradeFee: null,
    commision: null,
    remark: '',
  })

  const handleInputChange = (field, value) => {
    registData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSave = () => {
    alert('저장')
    return
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={5}>
        <SaveToolbar text="매출 장부 등록" onClick={handleSave}></SaveToolbar>
        <Stack gap={3}>
          <Grid container gap={5}>
            <AddressL1
              onChange={(value) => {
                handleInputChange('addressL1', value)
              }}
            />
            <AddressL2
              addressLevel1={registData.addressL1}
              onChange={(value) => {
                handleInputChange('addressL2', value)
              }}
            />
            <InputADdressL3
              value={registData.addressL3}
              onChange={(e) => {
                handleInputChange('addressL3', e.target.value)
              }}
            />
          </Grid>
        </Stack>
      </Stack>
    </Box>
  )
}

RegistRevenuePage.displayName = 'RegistRevenuePage'

export default RegistRevenuePage
