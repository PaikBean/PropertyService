const { TextField } = require('@mui/material')

const CompanyName = ({ value, sx }) => {
  return (
    <TextField
      sx={{
        ...sx,
        backgroundColor: 'grey.200', // 배경색을 회색으로 설정
      }}
      value={value}
      id="company-name"
      label="Company Name"
      name="companyName"
      fullWidth
      variant="outlined"
      type="companyName"
      margin="normal"
      autoComplete="companyName"
      InputProps={{
        readOnly: true, // 읽기 전용으로 설정
      }}
    />
  )
}

CompanyName.displayName = 'CompanyName'

export default CompanyName
