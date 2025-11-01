import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

class WebSocketService {
  connect(donorId, bloodGroup, organType, city, onMessage) {
    const socket = new SockJS('http://localhost:8080/ws-notifications');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, () => {
      this.stompClient.subscribe(`/topic/donor/${donorId}`, onMessage);

      if (bloodGroup) {
        this.stompClient.subscribe(`/topic/blood-group/${bloodGroup}`, onMessage);
      }

      if (organType) {
        this.stompClient.subscribe(`/topic/organ-type/${organType}`, onMessage);
      }

      if (city) {
        this.stompClient.subscribe(`/topic/emergency/${city}`, onMessage);
      }

      console.log('✅ WebSocket connected');
    });
  }

  disconnect() {
    if (this.stompClient) {
      this.stompClient.disconnect(() => {
        console.log('❌ WebSocket disconnected');
      });
    }
  }
}

export default new WebSocketService();
