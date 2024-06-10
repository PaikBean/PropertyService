import { TextField } from '@mui/material'
import { useEffect } from 'react'

const InputSignUpPassword = ({ value, onChange, sx }) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      id="password"
      label="Password"
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
