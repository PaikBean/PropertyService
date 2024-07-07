import { TextField } from '@mui/material'
import { useEffect } from 'react'

const InputSignUpPassword = ({ value, onChange, sx, label = '비밀번호' }) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      id="password"
      label={label}
      name="password"
      required
      fullWidth
      variant="outlined"
      type="password"
      autoComplete="current-password"
    />
  )
}

InputSignUpPassword.displayName = 'InputSignUpPassword'

export default InputSignUpPassword
