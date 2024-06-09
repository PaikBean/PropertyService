import { useEffect, useState } from 'react'
import CheckSignUpPassword from '../textfield/CheckSignUpPassword'
import InputEmail from '../textfield/InputEmail'
import InputSignUpEmail from '../textfield/InputSignUpEmail'
import InputSignUpPassword from '../textfield/InputSignUpPassword'

const { Stack } = require('@mui/material')

const SignupFourthStep = ({ inputFourth, setInputFourth }) => {
  const [checkError, setCheckError] = useState(false)
  const handleInputChange = (field, value) => {
    setInputFourth((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  useEffect(() => {
    setCheckError(
      inputFourth.checkPassword.length > 0 &&
        inputFourth.managerPassword !== inputFourth.checkPassword
    )
  }, [inputFourth.managerPassword, inputFourth.checkPassword])

  const passwordsMatch =
    inputFourth.managerPassword &&
    inputFourth.checkPassword &&
    inputFourth.managerPassword === inputFourth.checkPassword

  return (
    <Stack gap={4} width="50%">
      <InputSignUpEmail
        value={inputFourth.managerEmail}
        onChange={(e) => {
          handleInputChange('managerEmail', e.target.value)
        }}
      />
      <InputSignUpPassword
        value={inputFourth.managerPassword}
        onChange={(e) => {
          handleInputChange('managerPassword', e.target.value)
        }}
      />
      <CheckSignUpPassword
        value={inputFourth.checkPassword}
        onChange={(e) => {
          handleInputChange('checkPassword', e.target.value)
          handleInputChange(
            'checkResult',
            inputFourth.managerPassword &&
              e.target.value &&
              inputFourth.managerPassword === e.target.value
          )
        }}
        error={checkError}
      />
    </Stack>
  )
}

SignupFourthStep.displayName = 'SignupFourthStep'

export default SignupFourthStep
