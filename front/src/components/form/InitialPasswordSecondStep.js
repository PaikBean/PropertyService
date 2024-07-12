// React, Next

// Materials
import { Stack } from '@mui/material'

// Custom Components
import InputSignUpPassword from '../textfield/InputSignUpPassword'
import CheckSignUpPassword from '../textfield/CheckSignUpPassword'
import { useEffect, useState } from 'react'
import SaveBtn from '../button/SaveBtn'

// Utils

const InitialPasswordSecondStep = ({
  inputSecond,
  setInputSecond,
  onClick,
}) => {
  const [checkError, setCheckError] = useState(false)
  const handleInputChange = (field, value) => {
    setInputSecond((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  useEffect(() => {
    setCheckError(
      inputSecond.password.length > 0 &&
        inputSecond.password !== inputSecond.checkPassword
    )
  }, [inputSecond.password, inputSecond.checkPassword])

  return (
    <Stack gap={5} width="70%">
      <InputSignUpPassword
        value={inputSecond.password}
        onChange={(e) => {
          handleInputChange('password', e.target.value)
        }}
      />
      <CheckSignUpPassword
        value={inputSecond.checkPassword}
        onChange={(e) => {
          handleInputChange('checkPassword', e.target.value)
          handleInputChange(
            'checkResult',
            inputSecond.password &&
              e.target.value &&
              inputSecond.password === e.target.value
          )
        }}
        error={checkError}
      />
      <SaveBtn onClick={onClick} />
    </Stack>
  )
}

InitialPasswordSecondStep.displayName = 'InitialPasswordSecondStep'

export default InitialPasswordSecondStep
