import { Button, Grid, Stack, Typography } from '@mui/material'
import CashTextField from '../textfield/CashTextField'
import CashTransWonTextField from '../textfield/CashTransWonTextField'

const TrasactionTypePriceForm = ({ value, onChange, readOnly }) => {
  const renderInputs = () => {
    switch (value.transactionType) {
      case 1: // 월세
        return (
          <Grid container gap={3}>
            <CashTextField
              label="보증금"
              value={value.deposit}
              onChange={(e) => onChange('deposit', e.target.value)}
              readOnly={readOnly}
            />
            <CashTransWonTextField value={value.deposit} readOnly={readOnly} />
            <CashTextField
              label="월세"
              value={value.monthlyFee}
              onChange={(e) => onChange('monthlyFee', e.target.value)}
              readOnly={readOnly}
            />
            <CashTransWonTextField
              value={value.monthlyFee}
              readOnly={readOnly}
            />
          </Grid>
        )
      case 4: // 단기
        return (
          <Grid container gap={3}>
            <CashTextField
              label="단기 보증금"
              value={value.shortemDeposit}
              onChange={(e) => onChange('shortemDeposit', e.target.value)}
              readOnly={readOnly}
            />
            <CashTransWonTextField
              value={value.shortemDeposit}
              readOnly={readOnly}
            />
            <CashTextField
              label="단기 월세"
              value={value.shortemMonthlyFee}
              onChange={(e) => onChange('shortemMonthlyFee', e.target.value)}
              readOnly={readOnly}
            />
            <CashTransWonTextField
              value={value.shortemMonthlyFee}
              readOnly={readOnly}
            />
          </Grid>
        )
      case 2: // 전세
        return (
          <Grid container gap={3}>
            <CashTextField
              label="전세금"
              value={value.jeonseFee}
              onChange={(e) => onChange('jeonseFee', e.target.value)}
              readOnly={readOnly}
            />
            <CashTransWonTextField
              value={value.jeonseFee}
              readOnly={readOnly}
            />
          </Grid>
        )
      case 3: // 매매
        return (
          <Grid container gap={3}>
            <CashTextField
              label="매매금"
              value={value.tradeFee}
              onChange={(e) => onChange('tradeFee', e.target.value)}
              readOnly={readOnly}
            />
            <CashTransWonTextField value={value.tradeFee} readOnly={readOnly} />
          </Grid>
        )
      default:
        return null
    }
  }

  return (
    <Stack spacing={2}>
      <Typography variant="h6">거래 유형</Typography>
      <Stack direction="row" spacing={2}>
        <Button
          onClick={() => onChange('transactionType', 1)}
          sx={{
            backgroundColor:
              value.transactionType === 1 ? '#56866f' : '#e0e0e0',
            color: value.transactionType === 1 ? '#fff' : '#56866f',
            boxShadow: 'none',
            '&:hover': {
              backgroundColor:
                value.transactionType === 1 ? '#456358' : '#d0d0d0',
              boxShadow: 'none',
            },
            pointerEvents: readOnly ? 'none' : 'auto',
          }}
        >
          월세
        </Button>
        <Button
          onClick={() => onChange('transactionType', 2)}
          sx={{
            backgroundColor:
              value.transactionType === 2 ? '#56866f' : '#e0e0e0',
            color: value.transactionType === 2 ? '#fff' : '#56866f',
            boxShadow: 'none',
            '&:hover': {
              backgroundColor:
                value.transactionType === 2 ? '#456358' : '#d0d0d0',
              boxShadow: 'none',
            },
            pointerEvents: readOnly ? 'none' : 'auto',
          }}
        >
          전세
        </Button>
        <Button
          onClick={() => onChange('transactionType', 3)}
          sx={{
            backgroundColor:
              value.transactionType === 3 ? '#56866f' : '#e0e0e0',
            color: value.transactionType === 3 ? '#fff' : '#56866f',
            boxShadow: 'none',
            '&:hover': {
              backgroundColor:
                value.transactionType === 3 ? '#456358' : '#d0d0d0',
              boxShadow: 'none',
            },
            pointerEvents: readOnly ? 'none' : 'auto',
          }}
        >
          매매
        </Button>
        <Button
          onClick={() => onChange('transactionType', 4)}
          sx={{
            backgroundColor:
              value.transactionType === 4 ? '#56866f' : '#e0e0e0',
            color: value.transactionType === 4 ? '#fff' : '#56866f',
            boxShadow: 'none',
            '&:hover': {
              backgroundColor:
                value.transactionType === 4 ? '#456358' : '#d0d0d0',
              boxShadow: 'none',
            },
            pointerEvents: readOnly ? 'none' : 'auto',
          }}
        >
          단기
        </Button>
      </Stack>
      {renderInputs()}
    </Stack>
  )
}

TrasactionTypePriceForm.displayName = 'TrasactionTypePriceForm'

export default TrasactionTypePriceForm
