import { TextField } from '@mui/material'

const InputEmail = ({ value, onChange, sx }) => {
  return (
    <TextField
      value={value}
      onChange={onChange}
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
