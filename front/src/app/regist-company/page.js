'use client'
import RegistCompanyFirstStep from '@/components/form/RegistCompanyFirstStep'
import RegistCompanySecondStep from '@/components/form/RegistCompnaySecondStep'
import { fetchRegistCompany } from '@/store/slices/registCompanySlice'
import {
  Alert,
  Box,
  Button,
  Container,
  Grid,
  Stack,
  Step,
  StepLabel,
  Stepper,
} from '@mui/material'
import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const steps = ['Validate Company', 'Basic Info']

export default function ReisgtCompany() {
  const router = useRouter
  const dispatch = useDispatch()
  const [currentStep, setCurrentStep] = useState(0)
  const [showAlert, setShowAlert] = useState(false)
  const { validateData, registData } = useSelector(
    (state) => state.registCompany
  )

  const [inputFirst, setInputFirst] = useState({
    bizNumber: '',
    bizStartDate: null,
    presidentName: '',
  })
  const [inputSecond, setInputSecond] = useState({
    companyName: '',
    addressLevel1: null,
    addressLevel2: null,
    addressLevel3: '',
    companyEmaill: '',
  })

  useEffect(() => {
    console.log(validateData)
  }, [validateData])

  useEffect(() => {
    console.log(registData)
  }, [registData])

  const handleNext = () => {
    if (currentStep === 0 && !validateData.validateResult) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    setCurrentStep((prevStep) => prevStep + 1)
  }

  const handleBack = () => {
    setCurrentStep((prevStep) => prevStep - 1)
  }

  const handleSubmit = async () => {
    if (
      !registData.bizNumber &&
      !registData.presidentName &&
      !registData.companyName &&
      registData.companyEmaill
    ) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    dispatch(fetchRegistCompany(registData))
    alert(registData.companyCode)
    router.push('/')
    return
  }

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <RegistCompanyFirstStep
            inputFirst={inputFirst}
            setInputFirst={setInputFirst}
          />
        )
      case 1:
        return (
          <RegistCompanySecondStep
            inputSecond={inputSecond}
            setInputSecond={setInputSecond}
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
      <Stack gap={5} width="100%">
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
        <Stepper activeStep={currentStep} alternativeLabel>
          {steps.map((label, index) => {
            return (
              <Step key={index}>
                <StepLabel>{label}</StepLabel>
              </Step>
            )
          })}
        </Stepper>
        <Grid container>
          <Button
            disabled={currentStep === 0}
            onClick={handleBack}
            sx={{ mr: 1 }}
          >
            뒤로
          </Button>
          <Box sx={{ flex: '1 1 auto' }} />
          {currentStep === steps.length - 1 ? (
            <Button onClick={handleSubmit}>완료</Button>
          ) : (
            <Button onClick={handleNext}>다음</Button>
          )}
        </Grid>
      </Stack>
    </Container>
  )
}
