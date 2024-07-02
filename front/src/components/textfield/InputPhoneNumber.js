import { TextField } from '@mui/material'
import { formatPhoneNumber } from '@/utils/formatter/formatPhoneNumber'

const InputPhoneNumber = ({ value, onChange, name = 'tel', sx }) => {
  const handleChange = (event) => {
    const formattedPhoneNumber = formatPhoneNumber(event.target.value)
    onChange(formattedPhoneNumber) // 상위 컴포넌트의 핸들러를 호출합니다.
  }

  return (
    <TextField
      sx={sx}
      value={value}
      onChange={handleChange}
      label="Phone Number"
      id="phone-number"
      placeholder="000-0000-0000"
      fullWidth
      variant="outlined"
      type="tel"
      // autoComplete="tel"
      name={name}
    />
  )
}

InputPhoneNumber.displayName = 'InputPhoneNumber'

export default InputPhoneNumber
