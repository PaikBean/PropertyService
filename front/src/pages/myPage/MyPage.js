import Gender from '@/components/autocomplete/Gender'
import ManageSearchIcon from '@mui/icons-material/ManageSearch'
import InputName2 from '@/components/textfield/InputName2'
import InputPhoneNumber from '@/components/textfield/InputPhoneNumber'
import InputSignUpEmail from '@/components/textfield/InputSignUpEmail'
import SaveTogleToolbar from '@/components/toolbar/SaveTogleToolbar'
import {
  Box,
  Button,
  Dialog,
  Grid,
  IconButton,
  Stack,
  Typography,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Divider,
} from '@mui/material'
import { useEffect, useState } from 'react'

const MyPage = () => {
  const initialData = {
    managerId: null,
    managerName: '',
    managerGender: '',
    managerPhoneNumber: '',
    managerRegistrationDate: '',
    managerEmail: '',
    managerCompanyId: null,
    managerDepartmentId: null,
    managerCompanyName: '',
    managerDepartmentName: '',
    managerPosition: '',
    managerRank: '',
  }

  const [mode, setMode] = useState(false)
  const [managerInfo, setManagerInfo] = useState(initialData)

  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false)

  useEffect(() => {
   alert("초기 정보 로딩") 
  }, [managerInfo.managerId])

  const handleInputChange = (field, value) => {
    setManagerInfo((prev) => ({
      ...prev,
      [field]: value,
    }))
  }


  const handleSave = () => {}

  const confirmDelete = () => {
    setIsDeleteDialogOpen(true)
  }

  const handleDeleteAccount = () => {
    // 실제 삭제 작업을 여기서 수행합니다.
    alert('탈퇴됨')
    setIsDeleteDialogOpen(false)
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Stack spacing={2}>
        <SaveTogleToolbar
          text={'마이 페이지'}
          onClick={handleSave}
          onChange={() => {
            setMode(!mode)
          }}
        ></SaveTogleToolbar>
        <Stack spacing={4}>
          <Divider></Divider>
          <Grid container gap={2} pl={4}>
            <Grid item xs={5.5}>
              <Stack spacing={3}>
                <Typography variant="h6">개인 정보</Typography>
                <Grid container gap={5}>
                  <Grid item xs={3}>
                    <InputName2
                      label="성명"
                      value={managerInfo.managerName}
                      onChange={(e) => {
                        handleInputChange('managerName', e.target.value)
                      }}
                      name="managerName"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : 'inherit', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={2}>
                    <Gender
                      value={managerInfo.managerGender}
                      onChange={(value) => {
                        handleInputChange('managerGender', value)
                      }}
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : '', // 커서 변경
                        },
                      }}
                      label="성별"
                    />
                  </Grid>
                </Grid>
                <Grid container gap={5}>
                  <Grid item xs={6}>
                    <InputPhoneNumber
                      value={managerInfo.managerPhoneNumber}
                      onChange={(formattedPhoneNumber) =>
                        handleInputChange(
                          'managerPhoneNumber',
                          formattedPhoneNumber
                        )
                      }
                      name="managerPhoneNumber"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : 'inherit', // 커서 변경
                        },
                      }}
                      label="전화번호"
                    />
                  </Grid>
                </Grid>
                <Grid container gap={5}>
                  <Grid item xs={8}>
                    <InputSignUpEmail
                      value={managerInfo.managerEmail}
                      onChange={(e) => {
                        handleInputChange('managerEmail', e.target.value)
                      }}
                      readOnly={false}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
                        },
                      }}
                      label="E-Mail Address"
                    />
                  </Grid>
                </Grid>
                <Grid container gap={5}>
                  <Grid item xs={8}>
                    {mode ? (
                      <Button color="error" onClick={confirmDelete}>
                        회원탈퇴
                      </Button>
                    ) : null}
                  </Grid>
                </Grid>
              </Stack>
            </Grid>
            <Grid item xs={5.5}>
              <Stack spacing={3}>
                <Typography variant="h6">업무 정보</Typography>
                <Grid container gap={5}>
                  <Grid item xs={4}>
                    <InputName2
                      label="회사명"
                      value={managerInfo.managerCompanyName}
                      onChange={(e) => {
                        handleInputChange('managerCompanyName', e.target.value)
                      }}
                      name="managerCompanyName"
                      readOnly={false}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={5}>
                    <InputName2
                      label="부서명"
                      value={managerInfo.managerDepartmentName}
                      onChange={(e) => {
                        handleInputChange(
                          'managerDepartmentName',
                          e.target.value
                        )
                      }}
                      name="managerDepartmentName"
                      readOnly={false}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                          cursor: 'not-allowed', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                </Grid>
                <Grid container gap={5}>
                  <Grid item xs={4}>
                    <InputName2
                      label="직무"
                      value={managerInfo.managerPosition}
                      onChange={(e) => {
                        handleInputChange('managerPosition', e.target.value)
                      }}
                      name="managerPosition"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : 'inherit', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                  <Grid item xs={4}>
                    <InputName2
                      label="직급"
                      value={managerInfo.managerRank}
                      onChange={(e) => {
                        handleInputChange('managerRank', e.target.value)
                      }}
                      name="managerRank"
                      readOnly={!mode}
                      sx={{
                        '& .MuiInputBase-root': {
                          backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                          cursor: !mode ? 'not-allowed' : 'inherit', // 커서 변경
                        },
                      }}
                    />
                  </Grid>
                </Grid>
                <Divider></Divider>
                <Grid container alignItems="center" gap={2}>
                  <Grid item>
                    <Typography>담당 매물:</Typography>
                  </Grid>
                  <Grid item>
                    <Typography> 개</Typography>
                  </Grid>
                  <Grid item>
                    {/* <IconButton onClick={() => {}} size="large">
                    <ManageSearchIcon fontSize="large" />
                  </IconButton> */}
                  </Grid>
                </Grid>
                <Grid container alignItems="center" gap={2}>
                  <Grid item>
                    <Typography>담당 고객:</Typography>
                  </Grid>
                  <Grid item>
                    <Typography> 명</Typography>
                  </Grid>
                  <Grid item>
                    {/* <IconButton onClick={() => {}} size="large">
                    <ManageSearchIcon fontSize="large" />
                  </IconButton> */}
                  </Grid>
                </Grid>
                <Grid container alignItems="center" gap={2}>
                  <Grid item>
                    <Typography>이번 달 누적 매출:</Typography>
                  </Grid>
                  <Grid item>
                    <Typography> 원</Typography>
                  </Grid>
                </Grid>
                <Grid container alignItems="center" gap={2}>
                  <Grid item>
                    <Typography>누적 개인 매출:</Typography>
                  </Grid>
                  <Grid item>
                    <Typography> 원</Typography>
                  </Grid>
                </Grid>
              </Stack>
            </Grid>
          </Grid>
        </Stack>
      </Stack>
      <Dialog
        open={isDeleteDialogOpen}
        onClose={() => setIsDeleteDialogOpen(false)}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{'회원 탈퇴'}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setIsDeleteDialogOpen(false)}>취소</Button>
          <Button onClick={handleDeleteAccount} color="primary" autoFocus>
            삭제
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  )
}

MyPage.displayName = 'MyPage'

export default MyPage
