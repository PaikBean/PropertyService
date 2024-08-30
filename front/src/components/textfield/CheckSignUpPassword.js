import { TextField } from '@mui/material'

const CheckSignUpPassword = ({
  value,
  onChange,
  sx,
  error,
  label = '비밀번호 확인',
}) => {
  return (
    <TextField
      sx={sx}
      value={value}
      onChange={onChange}
      error={error}
      helperText={error ? 'Passwords do not match' : ''}
      id="check-password"
      label={label}
      name="checkPassword"
      required
      fullWidth
      variant="outlined"
      type="password"
      autoComplete="current-password"
    />
  )
}

CheckSignUpPassword.displayName = 'CheckSignUpPassword'

export default CheckSignUpPassword
