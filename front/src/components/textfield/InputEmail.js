import { TextField } from '@mui/material'

const InputEmail = (sx) => {
  return (
    <TextField
      sx={sx}
      id="email"
      label="Email Address"
      name="email"
      required
      fullWidth
      variant="outlined"
      type="email"
      margin="normal"
      autoComplete="email"
    />
  )
}

InputEmail.displayName = 'InputEmail'

export default InputEmail
