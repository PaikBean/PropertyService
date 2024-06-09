import { TextField } from '@mui/material'

const InputPassword = (sx) => {
  return (
    <TextField
      sx={sx}
      id="password"
      label="Password"
      name="password"
      required
      fullWidth
      variant="outlined"
      type="password"
      margin="normal"
      autoComplete="current-password"
    />
  )
}

InputPassword.displayName = 'InputPassword'

export default InputPassword
