import { TextField } from '@mui/material'

const InputEmail = ({ value, onChange, sx, label = 'Email Address' }) => {
  return (
    <TextField
      value={value}
      onChange={onChange}
      sx={sx}
      id="email"
      label={label}
      name="email"
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
