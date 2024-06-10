import { TextField } from '@mui/material'

const InputBizNumber = ({ value, onChange, sx }) => {
  return (
    <TextField
      value={value}
      onChange={onChange}
      sx={sx}
      id="bizNumber"
      label="사업자등록번호"
      name="bizNumber"
      required
      fullWidth
      variant="outlined"
      type="bizNumber"
      autoComplete="bizNumber"
    />
  )
}

InputBizNumber.displayName = 'InputBizNumber'

export default InputBizNumber
