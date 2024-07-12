// React, Next

// Materials
import { TextField } from '@mui/material'

// Custom Components

// Utils
import { formatPhoneNumber } from '@/utils/formatter/formatPhoneNumber'

const InputPhoneNumber = ({
  value,
  onChange,
  name = 'tel',
  sx,
  readOnly,
  label = 'Phone Number',
}) => {
  const handleChange = (event) => {
    const formattedPhoneNumber = formatPhoneNumber(event.target.value)
    onChange(formattedPhoneNumber) // 상위 컴포넌트의 핸들러를 호출합니다.
  }

  return (
    <TextField
      sx={sx}
      value={value}
      onChange={handleChange}
      label={label}
      id="phone-number"
      placeholder="000-0000-0000"
      fullWidth
      variant="outlined"
      type="tel"
      // autoComplete="tel"
      name={name}
      InputProps={{
        readOnly: readOnly, // readOnly 속성 설정
      }}
    />
  )
}

InputPhoneNumber.displayName = 'InputPhoneNumber'

export default InputPhoneNumber
