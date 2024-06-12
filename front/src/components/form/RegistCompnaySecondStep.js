import AddressL1 from '../autocomplete/AddressL1'
import AddressL2 from '../autocomplete/AddressL2'
import InputADdressL3 from '../textfield/InputAddressL3'
import InputName2 from '../textfield/InputName2'

const { Stack } = require('@mui/material')

const RegistCompanySecondStep = ({ inputSecond, setInputSecond }) => {
  const handleInputChange = (field, value) => {
    setInputSecond((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack gap={5} width="50%">
      <InputName2
        label="회사명"
        value={inputSecond.companyName}
        onChange={(e) => {
          handleInputChange('companyName', e.target.value)
        }}
      />
      <AddressL1
        onChange={(value) => {
          handleInputChange('addressLevel1', value)
        }}
      />
      <AddressL2
        addressLevel1={inputSecond.addressLevel1}
        onChange={(value) => {
          handleInputChange('addressLevel2', value)
        }}
      />
      <InputADdressL3
        value={inputSecond.addressLevel3}
        onChange={(e) => {
          handleInputChange('addressLevel3', e.target.value)
        }}
      />
    </Stack>
  )
}

RegistCompanySecondStep.displayName = 'RegistCompanySecondStep'

export default RegistCompanySecondStep
