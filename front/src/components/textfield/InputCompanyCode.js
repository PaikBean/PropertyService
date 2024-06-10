import { TextField } from '@mui/material'

const InputCompanyCode = ({ value, onChange, sx }) => {
  return (
    <TextField
      value={value}
      onChange={onChange}
      sx={sx}
      id="company-code"
      label="Company Code"
      name="companyCode"
      required
      fullWidth
      variant="outlined"
      type="companyCode"
      margin="normal"
      autoComplete="companyCode"
    />
  )
}

InputCompanyCode.displayName = 'InputCompanyCode'

export default InputCompanyCode
