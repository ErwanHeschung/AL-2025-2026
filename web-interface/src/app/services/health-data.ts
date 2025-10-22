import { Injectable } from '@angular/core';

export interface QuestionnaireResponse {
  date: string;
  breathing: number;
  swelling: number;
  energy: number;
}

export interface HourlyData {
  hour: string;
  heartRate: number;
  spo2: number;
  activity: number;
}

export interface DailyMedicalData {
  date: string;
  hourlyData: HourlyData[];
}

@Injectable({
  providedIn: 'root'
})
export class HealthDataService {
  private readonly QUESTIONNAIRE_KEY = 'health_questionnaire_responses';
  private readonly MEDICAL_DATA_KEY = 'medical_data';

  saveQuestionnaireResponse(response: QuestionnaireResponse): void {
    const responses = this.getAllQuestionnaireResponses();
    const existingIndex = responses.findIndex(r => r.date === response.date);

    if (existingIndex !== -1) {
      responses[existingIndex] = response;
    } else {
      responses.push(response);
    }

    localStorage.setItem(this.QUESTIONNAIRE_KEY, JSON.stringify(responses));
  }

  getQuestionnaireResponse(date: string): QuestionnaireResponse | null {
    const responses = this.getAllQuestionnaireResponses();
    return responses.find(r => r.date === date) || null;
  }

  getAllQuestionnaireResponses(): QuestionnaireResponse[] {
    const data = localStorage.getItem(this.QUESTIONNAIRE_KEY);
    return data ? JSON.parse(data) : [];
  }

  saveMedicalData(data: DailyMedicalData): void {
    const allData = this.getAllMedicalData();
    const existingIndex = allData.findIndex(d => d.date === data.date);

    if (existingIndex !== -1) {
      allData[existingIndex] = data;
    } else {
      allData.push(data);
    }

    localStorage.setItem(this.MEDICAL_DATA_KEY, JSON.stringify(allData));
  }

  getMedicalData(date: string): DailyMedicalData | null {
    const allData = this.getAllMedicalData();
    return allData.find(d => d.date === date) || this.generateMockData(date);
  }

  getAllMedicalData(): DailyMedicalData[] {
    const data = localStorage.getItem(this.MEDICAL_DATA_KEY);
    return data ? JSON.parse(data) : [];
  }

  private generateMockData(date: string): DailyMedicalData {
    const hourlyData: HourlyData[] = [];
    for (let i = 0; i < 24; i++) {
      hourlyData.push({
        hour: `${i.toString().padStart(2, '0')}:00`,
        heartRate: Math.floor(Math.random() * 30) + 60,
        spo2: Math.floor(Math.random() * 5) + 95,
        activity: Math.floor(Math.random() * 100)
      });
    }
    return { date, hourlyData };
  }

  getDescriptionForValue(question: string, value: number): string {
    const descriptions: { [key: string]: { [key: number]: string } } = {
      breathing: {
        1: 'Not short of breath',
        2: 'Slightly short of breath',
        3: 'Mildly short of breath',
        4: 'Moderately short of breath',
        5: 'Short of breath',
        6: 'Quite short of breath',
        7: 'Very short of breath',
        8: 'Extremely short of breath',
        9: 'Severely short of breath'
      },
      swelling: {
        1: 'Not swollen',
        2: 'Barely swollen',
        3: 'Slightly swollen',
        4: 'Mildly swollen',
        5: 'Moderately swollen',
        6: 'Quite swollen',
        7: 'Very swollen',
        8: 'Extremely swollen',
        9: 'Severely swollen'
      },
      energy: {
        1: 'Very tired',
        2: 'Quite tired',
        3: 'Tired',
        4: 'Somewhat tired',
        5: 'Moderate energy',
        6: 'Good energy',
        7: 'Very good energy',
        8: 'Great energy',
        9: 'Full of energy'
      }
    };
    return descriptions[question]?.[value] || '';
  }
}
