// React, Next
import { useEffect, useState } from 'react'

// Materials
import { Alert, Button, Grid, Stack } from '@mui/material'

// Custom Components
import InputSignUpEmail from '../textfield/InputSignUpEmail'
import InputSignUpPassword from '../textfield/InputSignUpPassword'
import CheckSignUpPassword from '../textfield/CheckSignUpPassword'

// Utils

const SignUpThirdStep = ({
  registAccount,
  setRegistAccount,
  stepFlag,
  handleValidEmail,
}) => {
  const [checkError, setCheckError] = useState(false)

  const handleInputChange = (field, value) => {
    setRegistAccount((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  useEffect(() => {
    setCheckError(
      registAccount.checkPassword.length > 0 &&
        registAccount.managerPassword !== registAccount.checkPassword
    )
  }, [registAccount.managerPassword, registAccount.checkPassword])

  return (
    <Stack gap={4} width="60%">
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={9.5}>
          <InputSignUpEmail
            value={registAccount.managerEmail}
            onChange={(e) => {
              handleInputChange('managerEmail', e.target.value)
            }}
            label="이메일"
          />
        </Grid>
        <Grid item xs={2}>
          <Button
            onClick={handleValidEmail}
            fullWidth
            variant="contained"
            sx={{
              height: '55px',
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
      {registAccount.duplicateEmail ? (
        stepFlag.thirdEmail ? (
          <Alert>중복 확인되었습니다.</Alert>
        ) : (
          <Alert severity="error">등록된 이메일 입니다.</Alert>
        )
      ) : null}
      <InputSignUpPassword
        value={registAccount.managerPassword}
        onChange={(e) => {
          handleInputChange('managerPassword', e.target.value)
        }}
      />
      <CheckSignUpPassword
        value={registAccount.checkPassword}
        onChange={(e) => {
          handleInputChange('checkPassword', e.target.value)
          handleInputChange(
            'checkResult',
            registAccount.managerPassword &&
              e.target.value &&
              registAccount.managerPassword === e.target.value
          )
        }}
        error={checkError}
      />
    </Stack>
  )
}
SignUpThirdStep.displayName = 'SignUpThirdStep'

export default SignUpThirdStep
