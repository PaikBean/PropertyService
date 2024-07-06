import { useState } from 'react'
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

const SchedulePage = () => {
  const initScheduleInfo = {
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

  const [events, setEvents] = useState([
    { id: '1', title: 'Event 1', date: '2024-07-10', eventId: 1 },
    { id: '2', title: 'Event 2', date: '2024-07-12', eventId: 2 },
  ])

  const handleDateClick = (arg) => {
    // Date 클릭 시 이벤트 처리
    alert(arg.dateStr)
  }
  const handleEventClick = (info) => {
    // Event 클릭 시 이벤트 처리
    alert(
      `Event ID: ${info.event.extendedProps.eventId}\nEvent: ${info.event.title}\nDate: ${info.event.startStr}`
    )
  }

  const handleInputChange = (field, value) => {
    setScheduleInfo((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const hanleAddSchedule = () => {}

  const handleSave = () => {}

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
              <InputName2
                label="고객"
                value={scheduleInfo.clientName}
                onChange={(e) => {
                  handleInputChange('clientName', e.target.value)
                }}
                name="clientName"
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
                value={scheduleInfo.scheduleTypeId} // Add this line
                onChange={(value) => {
                  handleInputChange('scheduleTypeId', value)
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
                width={395}
                rows={2}
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
    </Box>
  )
}

export default SchedulePage
