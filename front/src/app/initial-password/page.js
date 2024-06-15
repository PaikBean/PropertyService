'use client'
import InitialPasswordFirstStep from '@/components/form/InitialPasswordFirstStep'
import InitialPasswordSecondStep from '@/components/form/InitialPasswordSecondStep'
import {
  fetchInitalPassword,
  fetchValidateManager,
} from '@/store/slices/initialPasswordSlice'
import { Alert, Box, Container } from '@mui/material'
import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'

export default function FindPassword() {
  const router = useRouter()
  const dispatch = useDispatch()
  const [currentStep, setCurrentStep] = useState(0)
  const [showAlert, setShowAlert] = useState(false)
  const [inputFirst, setInputFirst] = useState({
    email: null,
    companyCode: null,
  })
  const [inputSecond, setInputSecond] = useState({
    managerId: null,
    password: '',
    checkPassword: '',
    checkResult: false,
  })
  const { validateManager, initalPassword } = useSelector(
    (state) => state.initialPassword
  )
  useEffect(() => {
    console.log(inputSecond.checkResult)
  }, [inputSecond])

  useEffect(() => {
    if (initalPassword.managerId !== null)
      setCurrentStep((prevStep) => prevStep + 1)
  }, [initalPassword.managerId])

  useEffect(() => {
    if (initalPassword.status === 'succeeded') {
      alert('변경됨')
      router.push('/')
    }
  }, [initalPassword.status, router])

  const handleSearch = () => {
    if (currentStep === 0 && !inputFirst.companyCode && !inputFirst.email) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    dispatch(fetchValidateManager(inputFirst))
  }

  const handleSubmit = () => {
    if (currentStep === 1 && !inputSecond.checkResult) {
      console.log(inputSecond.checkResult)
      return
    }
    dispatch(fetchInitalPassword(inputSecond))
  }

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <InitialPasswordFirstStep
            inputFirst={inputFirst}
            setInputFirst={setInputFirst}
            handleSearch={handleSearch}
          />
        )
      case 1:
        return (
          <InitialPasswordSecondStep
            inputSecond={inputSecond}
            setInputSecond={setInputSecond}
            onClick={handleSubmit}
          />
        )
      default:
        return 'Unknown step'
    }
  }

  return (
    <Container
      sx={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh',
        maxWidth: '1000px',
      }}
    >
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          width: '100%',
        }}
      >
        {getStepContent(currentStep)}
        {showAlert ? (
          <Alert severity="error" sx={{ mt: '20px' }}>
            Check Your Input Value
          </Alert>
        ) : null}
      </Box>
    </Container>
  )
}
