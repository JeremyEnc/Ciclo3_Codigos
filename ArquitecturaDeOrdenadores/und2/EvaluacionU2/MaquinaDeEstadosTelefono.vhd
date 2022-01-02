library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity MaquinaDeEstadosTelefono is
    Port ( clk,llamar,nv,cr,dm,lf : in  STD_LOGIC;
           reposo,marcando,llamando,espera,finalizado : out  STD_LOGIC;
			  Q: out STD_LOGIC_VECTOR(3 downto 0));
end MaquinaDeEstadosTelefono;

architecture Behavioral of MaquinaDeEstadosTelefono is

TYPE estados is(A,B,C,D,E,F,G,H,I);
signal estAct, estFut: estados;

begin

	process(estAct, llamar, nv, cr, dm, lf)
	begin
		case estAct is
			
			when A =>
				reposo <= '1'; marcando <= '0'; llamando <= '0'; espera <= '0';finalizado <= '0';
				Q <= "0000";
				
				if(llamar = '1') then
					estFut <= B;
				else
					estFut <= A;
				end if;
			when B =>
				reposo <= '0'; marcando <= '1'; llamando <= '0'; espera <= '0';finalizado <= '0';
				Q <= "0001";
				
				if(nv = '1') then
					estFut <= C;
				else
					estFut <= B;
				end if;
			when C =>
				reposo <= '0'; marcando <= '0'; llamando <= '1'; espera <= '0';finalizado <= '0';
				Q <= "0010";
				
				if(cr = '1') then
					estFut <= D;
				else
					estFut <= F;
				end if;
			when D =>
				reposo <= '0'; marcando <= '0'; llamando <= '0'; espera <= '1';finalizado <= '0';
				Q <= "0011";
				
				if(lf = '1') then
					estFut <= E;
				else
					estFut <= D;
				end if;
			when E =>
				reposo <= '0'; marcando <= '0'; llamando <= '0'; espera <= '0';finalizado <= '1';
				Q <= "0100";
				
				estFut <= A;
			when F =>
				reposo <= '0'; marcando <= '0'; llamando <= '0'; espera <= '0';finalizado <= '0';
				Q <= "0101";
				
				if(dm = '1') then
					estFut <= G;
				else
					estFut <= I;
				end if;
			when G =>
				reposo <= '0'; marcando <= '0'; llamando <= '0'; espera <= '1';finalizado <= '0';
				Q <= "0110";
				
				if(lf = '1') then
					estFut <= H;
				else
					estFut <= G;
				end if;
			when H =>
				reposo <= '1'; marcando <= '0'; llamando <= '0'; espera <= '0';finalizado <= '1';
				Q <= "0111";
				
				estFut <= A;
			when I =>
				reposo <= '0'; marcando <= '0'; llamando <= '0'; espera <= '0';finalizado <= '1';
				Q <= "1000";
				
				estFut <= A;
			when others =>
				estFut <= A;
				report "hola " ;
			end case;
		end process;
			
		process(clk)
		begin
			if(clk'event and clk = '1') then
				estAct <= estFut;
			end if;
		end process;


end Behavioral;

