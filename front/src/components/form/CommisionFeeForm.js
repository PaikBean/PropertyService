import { Grid, Stack, Typography } from '@mui/material'
import CashTextField from '../textfield/CashTextField'
import CashTransWonTextField from '../textfield/CashTransWonTextField'

const CommisionFeeForm = ({ value, onChange }) => {
  return (
    <Stack spacing={1}>
      <Typography variant="h6">중개 수수료</Typography>
      <Grid container gap={3}>
        <CashTextField value={value} onChange={onChange} />
        <CashTransWonTextField value={value} />
      </Grid>
    </Stack>
  )
}

CommisionFeeForm.displayName = 'CommisionFeeForm'

export default CommisionFeeForm
