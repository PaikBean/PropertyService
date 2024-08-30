// React, Next

// Materials
import { Grid, Stack } from '@mui/material'

// Custom Components
import InputPhoneNumber from '../textfield/InputPhoneNumber'
import Gender from '../autocomplete/Gender'
import InputName2 from '../textfield/InputName2'
import ManagerState from '../autocomplete/ManagerState'
import Department2 from '../autocomplete/Department2'

// Utils

const SignUpSecondStep = ({ managerInfo, setManagerInfo, companyCode }) => {
  const handleInputChange = (field, value) => {
    setManagerInfo((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack spacing={3} width="70%">
      <Department2
        value={managerInfo.departmentId}
        onChange={(value) => {
          handleInputChange('departmentId', value)
        }}
        companyCode={companyCode}
        label="부서"
      />
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={4}>
          <InputName2
            label="이름"
            value={managerInfo.managerName}
            onChange={(e) => {
              handleInputChange('managerName', e.target.value)
            }}
          />
        </Grid>
        <Grid item xs={3.5}>
          <Gender
            value={managerInfo.managerGender}
            onChange={(value) => {
              handleInputChange('managerGender', value)
            }}
            label="성별"
          />
        </Grid>
        <Grid item xs={3.5}>
          <ManagerState
            value={managerInfo.managerStateId} // Add this line
            onChange={(value) => {
              handleInputChange('managerStateId', value)
            }}
            label="근무 상태"
          />
        </Grid>
      </Grid>
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={5.8}>
          <InputName2
            label="직급"
            value={managerInfo.managerRank}
            onChange={(e) => {
              handleInputChange('managerRank', e.target.value)
            }}
          />
        </Grid>
        <Grid item xs={5.8}>
          <InputName2
            label="직무"
            value={managerInfo.managerPosition}
            onChange={(e) => {
              handleInputChange('managerPosition', e.target.value)
            }}
          />
        </Grid>
      </Grid>
      <InputPhoneNumber
        value={managerInfo.managerPhoneNumber}
        onChange={(formattedPhoneNumber) =>
          handleInputChange('managerPhoneNumber', formattedPhoneNumber)
        }
        name="managerPhoneNumber"
        label="전화번호"
      />
    </Stack>
  )
}
SignUpSecondStep.displayName = 'SignUpSecondStep'

export default SignUpSecondStep
