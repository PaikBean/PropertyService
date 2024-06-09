import { Stack } from '@mui/material'
import Department from '../autocomplete/Department'
import InputRank from '../textfield/InputRank'
import InputPosition from '../textfield/InputPosition'
import ManagerState from '../autocomplete/ManagerState'
import InputEmail from '../textfield/InputEmail'
import InputPassword from '../textfield/InputPassword'
import InputManagerCode from '../textfield/InputManagerCode'

const SignUpThirdStep = ({ inputThird, setInputThird }) => {
  const handleInputChange = (field, value) => {
    setInputThird((prev) => ({
      ...prev,
      [field]: value,
    }))
  }
  return (
    <Stack gap={4} width="50%">
      <Department
        onChange={(value) => {
          handleInputChange('departmentId', value)
        }}
      />
      <InputRank
        value={inputThird.managerRank}
        onChange={(e) => {
          handleInputChange('managerRank', e.target.value)
        }}
      />
      <InputPosition
        value={inputThird.managerPosition}
        onChange={(e) => {
          handleInputChange('managerPosition', e.target.value)
        }}
      />
      <ManagerState
        onChange={(value) => {
          handleInputChange('state', value)
        }}
      />
      <InputManagerCode
        value={inputThird.managerCode}
        onChange={(e) => {
          handleInputChange('managerCode', e.target.value)
        }}
      />
    </Stack>
  )
}
SignUpThirdStep.displayName = 'SignUpThirdStep'

export default SignUpThirdStep
