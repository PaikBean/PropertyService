import { Stack } from '@mui/material'
import InputName from '../textfield/InputName'
import InputPhoneNumber from '../textfield/InputPhoneNumber'
import AddressL1 from '../autocomplete/AddressL1'
import AddressL2 from '../autocomplete/AddressL2'

import InputAddressL3 from '../textfield/InputAddressL3'
import Gender from '../autocomplete/Gender'

const SignUpSecondStep = ({ inputSecond, setInputSecond }) => {
  const handleInputChange = (field, value) => {
    setInputSecond((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack gap={4} width="50%">
      <InputName
        value={inputSecond.managerName}
        onChange={(e) => {
          handleInputChange('managerName', e.target.value)
        }}
      />
      <InputPhoneNumber
        value={inputSecond.managerPhoneNumber}
        onChange={(formattedPhoneNumber) =>
          handleInputChange('managerPhoneNumber', formattedPhoneNumber)
        }
      />
      <AddressL1
        value={inputSecond.managerAddressLevel1} // Add this line
        onChange={(value) => {
          handleInputChange('managerAddressLevel1', value)
        }}
      />
      <AddressL2
        value={inputSecond.managerAddressLevel2} // Add this line
        addressLevel1={inputSecond.managerAddressLevel1}
        onChange={(value) => {
          handleInputChange('managerAddressLevel2', value)
        }}
      />
      <InputAddressL3
        value={inputSecond.managerAddressLevel3}
        onChange={(e) => {
          handleInputChange('managerAddressLevel3', e.target.value)
        }}
      />
      <Gender
        value={inputSecond.gender}
        onChange={(value) => {
          handleInputChange('gender', value)
        }}
      />
    </Stack>
  )
}
SignUpSecondStep.displayName = 'SignUpSecondStep'

export default SignUpSecondStep
