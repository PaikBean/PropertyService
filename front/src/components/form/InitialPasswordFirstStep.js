// React, Next
import { Stack } from '@mui/material'

// Materials

// Custom Components
import InputEmail from '../textfield/InputEmail'
import InputCompanyCode from '../textfield/InputCompanyCode'
import SearchBtn from '../button/SearchBtn'

// Utils

const InitialPasswordFirstStep = ({
  inputFirst,
  setInputFirst,
  handleSearch,
}) => {
  const handleInputChange = (field, value) => {
    setInputFirst((prev) => ({
      ...prev,
      [field]: value,
    }))
  }
  return (
    <Stack gap={5} width="70%">
      <InputEmail
        value={inputFirst.email}
        onChange={(e) => {
          handleInputChange('email', e.target.value)
        }}
      />
      <InputCompanyCode
        value={inputFirst.companyCode}
        onChange={(e) => {
          handleInputChange('companyCode', e.target.value)
        }}
      />
      <SearchBtn onClick={handleSearch} />
    </Stack>
  )
}

InitialPasswordFirstStep.displayName = 'InitialPasswordFirstStep'

export default InitialPasswordFirstStep
