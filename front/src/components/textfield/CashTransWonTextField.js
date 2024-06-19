import { TextField } from '@mui/material'

const CashTransWonTextField = ({ value }) => {
  const formatNumberToKoreanCurrency = (numberValue) => {
    // 숫자를 '만', '억', '조' 등의 한국어 단위로 변환합니다.
    const trillion = Math.floor(numberValue / 1_0000_0000_0000)
    const billion = Math.floor((numberValue % 1_0000_0000_0000) / 1_0000_0000)
    const million = Math.floor((numberValue % 1_0000_0000) / 1_0000)

    const rest = numberValue % 1_0000

    return (
      `${trillion ? `${trillion}조 ` : ''}` +
      `${billion ? `${billion}억 ` : ''}` +
      `${million ? `${million}만 ` : ''}` +
      `${rest}원`
    )
  }

  return (
    <TextField
      value={formatNumberToKoreanCurrency(value)}
      variant="outlined"
      sx={{ width: 220 }}
      readOnly={true}
      InputProps={{
        readOnly: true,
      }}
    />
  )
}

CashTransWonTextField.displayName = 'CashTransWonTextField'

export default CashTransWonTextField
