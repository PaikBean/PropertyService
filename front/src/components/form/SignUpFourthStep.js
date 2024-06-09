import { useEffect, useState } from 'react'
import CheckSignUpPassword from '../textfield/CheckSignUpPassword'
import InputSignUpEmail from '../textfield/InputSignUpEmail'
import InputSignUpPassword from '../textfield/InputSignUpPassword'
import { useSelector } from 'react-redux'

const { Stack, Button, Grid, Alert } = require('@mui/material')

const fetchDuplicateEmail = async (email) => {
  try {
    const response = await fetch(`/api/manager/v1/duplicate?email=${email}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })
    if (!response.ok) {
      throw new Error('Network response was not ok')
    }
    const data = await response.json()
    return data
  } catch (error) {
    throw new Error(error.message)
  }
}

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

  const handleDuplicateEmail = async () => {
    try {
      const result = await fetchDuplicateEmail(inputFourth.managerEmail)
      if (result.data) {
        handleInputChange('duplicateEmail', true)
      } else {
        handleInputChange('duplicateEmail', false)
      }
    } catch (error) {
      setEmailError(true)
    }
  }

  return (
    <Stack gap={4} width="50%">
      <Grid container spacing={2}>
        <Grid item xs={9.5}>
          <InputSignUpEmail
            value={inputFourth.managerEmail}
            onChange={(e) => {
              handleInputChange('managerEmail', e.target.value)
            }}
          />
        </Grid>
        <Grid item xs={2}>
          <Button
            onClick={handleDuplicateEmail}
            fullWidth
            variant="contained"
            sx={{
              backgroundColor: '#56866fec',
              '&:hover': {
                backgroundColor: '#56866f',
              },
            }}
          >
            중복 확인
          </Button>
        </Grid>
      </Grid>
      {inputFourth.duplicateEmail ? (
        <Alert severity="error">등록된 이메일 입니다.</Alert>
      ) : (
        false
      )}
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
