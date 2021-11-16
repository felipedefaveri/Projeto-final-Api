package org.serratec.ecommerce.enums;

	
	public enum StatusPedido {
		ABERTO(0),
		PAGO(1),
		ENVIADO(2),
		ENTREGUE(3),
		CANCELADO(4);
		
		private Integer codigo;
		
		private StatusPedido(Integer codigo) {
			this.codigo = codigo;
		}
		
		public Integer getCodigo() {
			return codigo;
		}
		
		public static StatusPedido valueOf(Integer codigo) {
			for (StatusPedido status : StatusPedido.values()) {
				if (status.getCodigo() == codigo) {
					return status;
				}
			}
			throw new IllegalArgumentException("Código de status inválido!");
		}
	}
	
