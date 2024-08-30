// React, Next

// Materials
const { Stack } = require('@mui/material')

// Custom Components
import InputName2 from '../textfield/InputName2'
import InputSignUpEmail from '../textfield/InputSignUpEmail'

// Utils

const RegistCompanyThirdStep = ({ inputThird, setInputThird }) => {
  const handleInputChange = (field, value) => {
    setInputThird((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack spacing={4} width="50%">
      <InputName2
        label="사업체명"
        value={inputThird.companyName}
        onChange={(e) => {
          handleInputChange('companyName', e.target.value)
        }}
      />
      <InputSignUpEmail
        value={inputThird.companyEmail}
        onChange={(e) => {
          handleInputChange('companyEmail', e.target.value)
        }}
        label="사업체 이메일"
      />
      <InputName2
        label="사업체 연락처"
        value={inputThird.companyNumber}
        onChange={(e) => {
          handleInputChange('companyNumber', e.target.value)
        }}
      />
    </Stack>
  )
}

RegistCompanyThirdStep.displayName = 'RegistCompanyThirdStep'

export default RegistCompanyThirdStep
