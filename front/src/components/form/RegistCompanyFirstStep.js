import { Button, Grid, Stack, Typography } from '@mui/material'
import InputBizNumber from '../textfield/InputBizNumber'
import BasicDatePicker from '../datepicker/BasicDatePicker'
import InputName from '../textfield/InputName'
import InputName2 from '../textfield/InputName2'

const RegistCompanyFirstStep = ({ inputFirst, setInputFirst }) => {
  const handleInputChange = (field, value) => {
    setInputFirst((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack gap={5} width="50%">
      <InputBizNumber
        value={inputFirst.bizNumber}
        onChange={(e) => {
          handleInputChange('bizNumber', e.target.value)
        }}
      />
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <BasicDatePicker label="등록일" />
        </Grid>
        <Grid item xs={6}>
          <InputName2 label="대표자명" />
        </Grid>
      </Grid>

      <Button>검색</Button>
    </Stack>
  )
}

RegistCompanyFirstStep.displayName = 'RegistCompanyFirstStep'

export default RegistCompanyFirstStep
