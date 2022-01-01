library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity MaquinaDeRefrescos is
    Port ( clk, moneda, mp, precio, lc, ls : in  STD_LOGIC;
           cap, limpia, sirve, cambio, dec, im, q2, q1, q0 : out  STD_LOGIC);
end MaquinaDeRefrescos;

architecture Behavioral of MaquinaDeRefrescos is

	type estados is(A,B,C,D,E,F,G,H,I);
	signal estAct, estFut: estados;

begin

	process(estAct, moneda, mp, precio, lc, ls)
	begin
		case estAct is
			
			when A =>
				cap <= '0'; limpia <= '0'; sirve <= '0';
				cambio <= '0'; dec <= '0'; im <= '1';
				q2 <= '0'; q1 <= '0'; q0 <= '0';
				
				if(moneda = '1') then
					estFut <= B;
				else
					estFut <= A;
				end if;
			when B =>
				cap <= '0'; limpia <= '0'; sirve <= '0';
				cambio <= '0'; dec <= '0'; im <= '0';
				q2 <= '0'; q1 <= '0'; q0 <= '1';
				
				if(moneda = '0') then
					estFut <= C;
				else
					estFut <= B;
				end if;
			when C =>
				cap <= '0'; limpia <= '0'; sirve <= '0';
				cambio <= '0'; dec <= '0'; im <= '0';
				q2 <= '0'; q1 <= '1'; q0 <= '0';
				
				if(mp = '0') then
					estFut <= D;
				else
					estFut <= A;
				end if;
			when D =>
				cap <= '0'; limpia <= '0'; sirve <= '0';
				cambio <= '0'; dec <= '0'; im <= '0';
				q2 <= '0'; q1 <= '1'; q0 <= '1';
				
				if(precio = '1') then
					estFut <= E;
				else
					estFut <= G;
				end if;
			when E =>
				cap <= '0'; limpia <= '0'; sirve <= '1';
				cambio <= '0'; dec <= '0'; im <= '0';
				q2 <= '1'; q1 <= '0'; q0 <= '0';
				
				if(ls = '1') then
					estFut <= F;
				else
					estFut <= E;
				end if;
			when F =>
				cap <= '0'; limpia <= '1'; sirve <= '0';
				cambio <= '0'; dec <= '0'; im <= '0';
				q2 <= '1'; q1 <= '0'; q0 <= '1';
				
				estFut <= A;
				
			when G =>
				cap <= '0'; limpia <= '0'; sirve <= '0';
				cambio <= '1'; dec <= '0'; im <= '0';
				q2 <= '1'; q1 <= '1'; q0 <= '0';
				
				if(lc = '1') then
					estFut <= H;
				else
					estFut <= G;
				end if;
			when H =>
				cap <= '0'; limpia <= '0'; sirve <= '0';
				cambio <= '0'; dec <= '1'; im <= '0';
				q2 <= '1'; q1 <= '1'; q0 <= '1';
				
				estFut <= D;
			when others =>		
				estFut <= A;
			end case;
		end process;
	
		process(clk)
		begin
				if(clk'event and clk = '1') then
					estAct <= estFut;
				end if;
		end process;



end Behavioral;

