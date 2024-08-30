import { useEffect, useMemo, useState } from 'react'
import FullCalendar from '@fullcalendar/react'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import {
  Box,
  Button,
  Divider,
  FormControlLabel,
  Grid,
  Stack,
  Switch,
} from '@mui/material'
import InputName2 from '@/components/textfield/InputName2'
import ManagerAutocomplete from '@/components/autocomplete/ManagerAutocomplete'
import ScheduleType from '@/components/autocomplete/scheduleType'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import BasicDatePicker from '@/components/datepicker/BasicDatePicker'
import dayjs from 'dayjs'
import Priority from '@/components/autocomplete/Priority'
import RemarkTextField from '@/components/textfield/RemarkTextField'
import AddScheduleModal2 from '@/components/modal/AddScheduleModal2'
import Clients from '@/components/autocomplete/Clients'
import { fetchSearchScheduleList } from './api/fetchSearchScheduleList'
import { fetchSearchSchedule } from './api/fetchSearchSchedule'
import { fetchUpdateSchedule } from './api/fetchUpdateSchedule'

const SchedulePage = () => {
  const initScheduleInfo = {
    scheduleId: null,
    managerId: null,
    clientId: null,
    clientName: '',
    scheduleTypeId: null,
    scheduleType: '',
    priority: '',
    scheduleDate: '',
    remark: '',
  }

  const [mode, setMode] = useState(false)

  const [scheduleInfo, setScheduleInfo] = useState(initScheduleInfo)

  const [isAddScheduleModalOpen, setIsAddScheduleModalOpen] = useState(false)

  const [events, setEvents] = useState([])


  const convertPriorityToKorean = (priority) => {
    switch(priority) {
      case "HIGH":
        return "상";
      case "MEDIUM":
        return "중";
      case "LOW":
        return "하";
      default:
        return "";
    }
  }

  const scheduleTypeColors = useMemo(() => ({
    BALANCE: '#FF5733',    // 잔금 - 주황색
    MEETING: '#33FF57',    // 미팅 - 초록색
    LEAVE: '#3357FF',      // 휴가 - 파란색
    MOVE_IN: '#FFD700',    // 입주 - 금색
    MOVE_OUT: '#FF4500',   // 퇴주 - 다홍색
  }), [])

  useEffect(() => {
    const fetchSchedules = async () => {
      try {
        const response = await fetchSearchScheduleList()
        // 서버에서 가져온 스케줄 데이터를 적절히 가공하여 이벤트로 설정
        const eventList = response.data.map((schedule) => {
          // scheduleDate가 유효한지 확인
          if (schedule.scheduleDateTxt) {
            const formattedDate = `${schedule.scheduleDateTxt.substring(0, 4)}-${schedule.scheduleDateTxt.substring(4, 6)}-${schedule.scheduleDateTxt.substring(6, 8)}`
            return {
              id: schedule.scheduleId.toString(),
              title: `${schedule.managerName}\t-\t${convertPriorityToKorean(schedule.priority)}`,
              date: formattedDate,  // 날짜 형식을 YYYY-MM-DD로 변환
              eventId: schedule.scheduleId,
              backgroundColor: scheduleTypeColors[schedule.scheduleType] || '#000', // ScheduleType에 따른 색상 설정
              borderColor: scheduleTypeColors[schedule.scheduleType] || '#000',     // ScheduleType에 따른 테두리 색상 설정
            }
          } else {
            console.warn(`Invalid schedule date for schedule ID: ${schedule.scheduleId}`)
            return null // scheduleDate가 없는 경우 null 반환
          }
        }).filter(event => event !== null) // null 값 제거
        setEvents(eventList)
      } catch (error) {
        console.error('Error fetching schedules:', error)
      }
    }
  
    fetchSchedules()
  }, [scheduleTypeColors])

  const handleDateClick = (arg) => {
    // Date 클릭 시 이벤트 처리
    // alert(arg.dateStr)
    console.log(arg)
    // try{
    //   const response = await fetchSearchSchedule(arg.data)
    // } catch (error){
    //   alert(error)
    // }
  }
  const handleEventClick = async (info) => {
    // Event 클릭 시 이벤트 처리
    // alert(
    //   `Event ID: ${info.event.extendedProps.eventId}\nEvent: ${info.event.title}\nDate: ${info.event.startStr}`
    // )
    console.log(info.event.extendedProps.eventId)
    try{
      const response = await fetchSearchSchedule(info.event.extendedProps.eventId)
      if(response.responseCode === "SUCCESS"){
        console.log(response.data)
        setScheduleInfo(response.data)
      } else{
        throw new Error(response.message)
      }
    } catch (error){
      alert(error)
    }
  }

  const handleInputChange = (field, value) => {
    setScheduleInfo((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const hanleAddSchedule = () => {
    setIsAddScheduleModalOpen(!isAddScheduleModalOpen)
  }

  const handleSave = async () => {
    try{
      const response = await fetchUpdateSchedule(scheduleInfo)
      if(response.responseCode === "SUCCESS"){
        setScheduleInfo(initScheduleInfo)
        alert("수정되었습니다.")
        try {
          const response = await fetchSearchScheduleList()
          // 서버에서 가져온 스케줄 데이터를 적절히 가공하여 이벤트로 설정
          const eventList = response.data.map((schedule) => {
            // scheduleDate가 유효한지 확인
            if (schedule.scheduleDateTxt) {
              const formattedDate = `${schedule.scheduleDateTxt.substring(0, 4)}-${schedule.scheduleDateTxt.substring(4, 6)}-${schedule.scheduleDateTxt.substring(6, 8)}`
              return {
                id: schedule.scheduleId.toString(),
                title: `${schedule.managerName}\t-\t${convertPriorityToKorean(schedule.priority)}`,
                date: formattedDate,  // 날짜 형식을 YYYY-MM-DD로 변환
                eventId: schedule.scheduleId,
                backgroundColor: scheduleTypeColors[schedule.scheduleType] || '#000', // ScheduleType에 따른 색상 설정
                borderColor: scheduleTypeColors[schedule.scheduleType] || '#000',     // ScheduleType에 따른 테두리 색상 설정
              }
            } else {
              console.warn(`Invalid schedule date for schedule ID: ${schedule.scheduleId}`)
              return null // scheduleDate가 없는 경우 null 반환
            }
          }).filter(event => event !== null) // null 값 제거
          setEvents(eventList)
        } catch (error) {
          console.error('Error fetching schedules:', error)
        }
      } else{
        throw new Error(response.message)
      }
    } catch (error){
      alert(error)
    }
  }

  const handleCloseModal = async () => {
    try {
      const response = await fetchSearchScheduleList()
      // 서버에서 가져온 스케줄 데이터를 적절히 가공하여 이벤트로 설정
      const eventList = response.data.map((schedule) => {
        // scheduleDate가 유효한지 확인
        if (schedule.scheduleDateTxt) {
          const formattedDate = `${schedule.scheduleDateTxt.substring(0, 4)}-${schedule.scheduleDateTxt.substring(4, 6)}-${schedule.scheduleDateTxt.substring(6, 8)}`
          return {
            id: schedule.scheduleId.toString(),
            title: `${schedule.managerName}\t-\t${convertPriorityToKorean(schedule.priority)}`,
            date: formattedDate,  // 날짜 형식을 YYYY-MM-DD로 변환
            eventId: schedule.scheduleType,
            backgroundColor: scheduleTypeColors[schedule.scheduleType] || '#000', // ScheduleType에 따른 색상 설정
            borderColor: scheduleTypeColors[schedule.scheduleType] || '#000',     // ScheduleType에 따른 테두리 색상 설정
          }
        } else {
          console.warn(`Invalid schedule date for schedule ID: ${schedule.scheduleId}`)
          return null // scheduleDate가 없는 경우 null 반환
        }
      }).filter(event => event !== null) // null 값 제거
      setEvents(eventList)
    } catch (error) {
      console.error('Error fetching schedules:', error)
    }
    setIsAddScheduleModalOpen ? setIsAddScheduleModalOpen(false) : null
  }

  return (
    <Box
      sx={{
        width: '100%',
      }}
    >
      <Grid container>
        <Grid item xs={9}>
          <FullCalendar
            plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
            initialView="dayGridMonth"
            events={events}
            height={'85vh'}
            // titleFormat={{ year: 'numeric', month: '2-digit' }} // 제목 형식 설정
            // titleFormat="dddd, MMMM D, YYYY"
            dateClick={handleDateClick}
            eventClick={handleEventClick} // 이벤트 클릭 핸들러 추가
          />
        </Grid>
        <Grid item xs={3}>
          <Stack spacing={4}>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Button
                  onClick={hanleAddSchedule}
                  fullWidth
                  variant="contained"
                  sx={{
                    backgroundColor: '#56866fec',
                    '&:hover': {
                      backgroundColor: '#56866f',
                    },
                  }}
                >
                  일정 추가
                </Button>
              </Grid>
            </Grid>

            {/* <Divider
              textAlign="left"
              sx={{ width: '100%', borderColor: 'black' }}
            ></Divider> */}
            <Stack spacing={3} padding={2}>
              <Grid container justifyContent={'space-between'}>
                <Grid item>
                  <FormControlLabel
                    sx={{
                      display: 'block',
                    }}
                    control={
                      <Switch
                        onChange={() => {
                          setMode(!mode)
                        }}
                        color="primary"
                      />
                    }
                  />
                </Grid>
                <Grid item>
                  {mode ? (
                    <Button
                      variant="contained"
                      onClick={handleSave}
                      sx={{
                        height: 35,
                        marginBottom: '1.5px',
                        backgroundColor: '#56866fec',
                        '&:hover': {
                          backgroundColor: '#56866f',
                        },
                      }}
                    >
                      저장
                    </Button>
                  ) : null}
                </Grid>
              </Grid>
              <ManagerAutocomplete
                value={scheduleInfo.managerId} // Add this line
                onChange={(value) => {
                  handleInputChange('managerId', value)
                }}
                label="담당 매니저"
                readOnly={!mode}
                sx={{
                  '& .MuiInputBase-root': {
                    backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                    cursor: !mode ? 'not-allowed' : '', // 커서 변경
                  },
                }}
              />
              <Clients
                value={scheduleInfo.clientId}
                onChange={(value) => {
                  handleInputChange('clientId', value)
                }}
                readOnly={!mode}
                sx={{
                  '& .MuiInputBase-root': {
                    backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                    cursor: !mode ? 'not-allowed' : 'inherit', // 커서 변경
                  },
                }}
              />
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <BasicDatePicker
                  label="일자"
                  value={dayjs(scheduleInfo.scheduleDate)}
                  onChange={(value) => {
                    handleInputChange('scheduleDate', value.format('YYYYMMDD'))
                  }}
                  readOnly={!mode}
                />
              </LocalizationProvider>
              <ScheduleType
                value={scheduleInfo.scheduleType} // Add this line
                onChange={(value) => {
                  handleInputChange('scheduleType', value)
                }}
                label="일정 유형"
                readOnly={!mode}
                sx={{
                  '& .MuiInputBase-root': {
                    backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                    cursor: !mode ? 'not-allowed' : '', // 커서 변경
                  },
                }}
              />
              <Priority
                value={scheduleInfo.priority}
                onChange={(value) => {
                  handleInputChange('priority', value)
                }}
                readOnly={!mode}
                sx={{
                  '& .MuiInputBase-root': {
                    backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                    cursor: !mode ? 'not-allowed' : '', // 커서 변경
                  },
                }}
              />
              <RemarkTextField
                value={scheduleInfo.remark}
                onChange={(e) => {
                  handleInputChange('remark', e.target.value)
                }}
                width={"100%"}
                rows={4}
                readOnly={!mode}
                sx={{
                  '& .MuiInputBase-root': {
                    backgroundColor: !mode ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                    cursor: !mode ? 'not-allowed' : 'inherit', // 커서 변경
                  },
                }}
              />
            </Stack>
          </Stack>
        </Grid>
      </Grid>
      <AddScheduleModal2
        open={isAddScheduleModalOpen}
        handleClose={handleCloseModal}
        data={{}}
      />
    </Box>
  )
}

export default SchedulePage
