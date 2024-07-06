import { Grid, Stack, Typography } from '@mui/material'
import CashTextField from '../textfield/CashTextField'
import CashTransWonTextField from '../textfield/CashTransWonTextField'

const CommisionFeeForm = ({ value, onChange, readOnly = false }) => {
  return (
    <Stack spacing={1}>
      <Typography variant="h6">중개 수수료</Typography>
      <Grid container gap={3}>
        <CashTextField value={value} onChange={onChange} readOnly={readOnly} />
        <CashTransWonTextField value={value} readOnly={readOnly} />
      </Grid>
    </Stack>
  )
}

CommisionFeeForm.displayName = 'CommisionFeeForm'

export default CommisionFeeForm
