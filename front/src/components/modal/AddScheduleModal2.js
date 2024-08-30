// React, Next
import { useState } from 'react'

// Materials
import { Modal, Stack } from '@mui/material'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'

// Custom Components
import Clients from '../autocomplete/Clients'
import RemarkTextField from '../textfield/RemarkTextField'
import ScheduleType from '../autocomplete/scheduleType'
import Priority from '../autocomplete/Priority'
import SaveToolbar from '../toolbar/SaveToolbar'
import ManagerAutocomplete from '../autocomplete/ManagerAutocomplete'
import BasicDatePicker from '@/components/datepicker/BasicDatePicker'

// Utils
import dayjs from 'dayjs'
import { fetchRegistSchedule } from '@/pages/schedule/api/fetchRegistSchedule'


const AddScheduleModal2 = ({ open, handleClose, data }) => {
  const initialData = {
    managerId: null,
    clientId: null,
    clientName: '',
    scheduleType: null,
    scheduleDate: '',
    priority: '',
    remark: '',
  }

  const [scheduleData, setScheduleData] = useState(initialData)

  const handleInputChange = (field, value) => {
    setScheduleData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }
  const handleSave = async () => {
    console.log(scheduleData)
    try {
      const result = await fetchRegistSchedule(scheduleData)
      setScheduleData(initialData)
      handleClose()
    } catch (error) {
      alert(error.message)
    }
    return
  }

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="data-modal-title"
      aria-describedby="data-modal-content"
    >
      <Stack
        spacing={3}
        sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          width: 800,
          bgcolor: 'background.paper',
          boxShadow: 24,
          p: 5,
        }}
      >
        <SaveToolbar text={'일정 추가'} onClick={handleSave}></SaveToolbar>
        <ManagerAutocomplete
          value={scheduleData.managerId}
          onChange={(value) => {
            handleInputChange('managerId', value)
          }}
        />
        <Clients
          value={scheduleData.clientId}
          onChange={(value) => {
            handleInputChange('clientId', value)
          }}
        />
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <BasicDatePicker
            label="일자"
            value={dayjs(scheduleData.scheduleDate)}
            onChange={(value) => {
              handleInputChange('scheduleDate', value.format('YYYYMMDD'))
            }}
          />
        </LocalizationProvider>
        <ScheduleType
          value={scheduleData.scheduleType}
          onChange={(value) => {
            handleInputChange('scheduleType', value)
          }}
        />
        <Priority
          value={scheduleData.priority}
          onChange={(value) => {
            handleInputChange('priority', value)
          }}
        />
        <RemarkTextField
          value={scheduleData.remark}
          onChange={(e) => {
            handleInputChange('remark', e.target.value)
          }}
          width={725}
          rows={2}
        />
      </Stack>
    </Modal>
  )
}

AddScheduleModal2.displayName = 'AddScheduleModal2'

export default AddScheduleModal2
