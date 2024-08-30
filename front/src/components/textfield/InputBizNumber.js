import { TextField } from '@mui/material'

const InputBizNumber = ({ value, onChange, sx, readOnly }) => {
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
      InputProps={{
        readOnly: readOnly, // readOnly 속성 설정
      }}
    />
  )
}

InputBizNumber.displayName = 'InputBizNumber'

export default InputBizNumber
