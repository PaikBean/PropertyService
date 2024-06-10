'use client'
import RegistCompanyFirstStep from '@/components/form/RegistCompanyFirstStep'
import RegistCompanySecondStep from '@/components/form/RegistCompnaySecondStep'
import {
  Box,
  Button,
  Container,
  Grid,
  Stack,
  Step,
  StepLabel,
  Stepper,
} from '@mui/material'
import { useState } from 'react'

const steps = ['Validate Company', 'Basic Info']

export default function ReisgtCompany() {
  const [currentStep, setCurrentStep] = useState(0)

  const [inputFirst, setInputFirst] = useState({
    bizNumber: '',
    bizStartDate: '',
    presidentName: '',
  })
  const [inputSecond, setInputSecond] = useState({
    addressLevel1: null,
    addressLevel2: null,
    addressLevel3: '',
  })

  const handleNext = () => {
    setCurrentStep((prevStep) => prevStep + 1)
  }

  const handleBack = () => {
    setCurrentStep((prevStep) => prevStep - 1)
  }

  const handleSubmit = async () => {
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
