const { TextField } = require('@mui/material')

const CompanyPresidentName = ({ value, sx }) => {
  return (
    <TextField
      sx={{
        ...sx,
        backgroundColor: 'grey.200', // 배경색을 회색으로 설정
      }}
      value={value}
      id="president-name"
      label="President Name"
      name="presidentName"
      fullWidth
      variant="outlined"
      type="presidentName"
      margin="normal"
      autoComplete="presidentName"
      InputProps={{
        readOnly: true, // 읽기 전용으로 설정
      }}
    />
  )
}

CompanyPresidentName.displayName = 'ComapnyName'

export default CompanyPresidentName
