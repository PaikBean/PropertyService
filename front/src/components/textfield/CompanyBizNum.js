const { TextField } = require('@mui/material')

const CompanyBizNum = ({ value, sx }) => {
  return (
    <TextField
      sx={{
        ...sx,
        backgroundColor: 'grey.200', // 배경색을 회색으로 설정
      }}
      value={value}
      id="company-biz-number"
      label="Company Biz Number"
      name="companyBizNumber"
      fullWidth
      variant="outlined"
      type="companyBizNumber"
      margin="normal"
      autoComplete="companyBizNumber"
      InputProps={{
        readOnly: true, // 읽기 전용으로 설정
      }}
    />
  )
}

CompanyBizNum.displayName = 'CompanyBizNum'

export default CompanyBizNum
