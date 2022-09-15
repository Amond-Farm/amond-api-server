// package com.amondfarm.api.service;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.amondfarm.api.domain.Member;
// import com.amondfarm.api.domain.enums.MemberStatus;
// import com.amondfarm.api.dto.ExperienceRequest;
// import com.amondfarm.api.dto.ExperienceResponse;
// import com.amondfarm.api.dto.MissionRequest;
// import com.amondfarm.api.dto.MissionResponse;
// import com.amondfarm.api.dto.SignUpRequest;
// import com.amondfarm.api.dto.SignUpResponse;
// import com.amondfarm.api.dto.WithdrawRequest;
// import com.amondfarm.api.dto.WithdrawResponse;
// import com.amondfarm.api.domain.enums.ProviderType;
// import com.amondfarm.api.repository.MemberRepository;
//
// import lombok.RequiredArgsConstructor;
//
// /**
//  * Member Service
//  *
//  * @since 2022-08-29
//  * @author jwlee
//  */
//
// @Service
// @RequiredArgsConstructor
// @Transactional(readOnly = true)
// public class MemberService {
//
// 	private final MemberRepository memberRepository;
//
// 	@Transactional
// 	public SignUpResponse join(SignUpRequest request) {
// 		Member member = request.toEntity();
// 		validateDuplicateMember(member);	// 중복회원 체크
// 		memberRepository.save(member);
// 		return new SignUpResponse("ok");
// 	}
//
// 	@Transactional
// 	public WithdrawResponse withdraw(WithdrawRequest request) {
// 		Member member = memberRepository.findMember(request.getProviderType(), request.getUid(), MemberStatus.ACTIVE)
// 			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
//
// 		member.changeStatus(MemberStatus.WITHDRAWAL);
// 		return new WithdrawResponse("ok");
// 	}
//
// 	public ExperienceResponse getExperience(ProviderType provider, String uid) {
//
// 		Member member = memberRepository.findMember(provider, uid, MemberStatus.ACTIVE)
// 			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
//
// 		return new ExperienceResponse(member.getExp());
// 	}
//
// 	@Transactional
// 	public ExperienceResponse updateExperience(ExperienceRequest request) {
//
// 		Member member = memberRepository.findMember(request.getProviderType(), request.getUid(), MemberStatus.ACTIVE)
// 			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
//
// 		member.changeExp(request.getExp());
// 		return new ExperienceResponse(member.getExp());
// 	}
//
// 	@Transactional
// 	public MissionResponse getMission(ProviderType provider, String uid) {
//
// 		Member member = memberRepository.findMember(provider, uid, MemberStatus.ACTIVE)
// 			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
//
// 		return new MissionResponse(member.getMission());
// 	}
//
// 	@Transactional
// 	public MissionResponse updateMission(MissionRequest request) {
// 		Member member = memberRepository.findMember(request.getProviderType(), request.getUid(), MemberStatus.ACTIVE)
// 			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
//
// 		member.changeMission(request.getMission());
// 		return new MissionResponse(member.getMission());
// 	}
//
// 	private void validateDuplicateMember(Member member) {
//
// 		memberRepository.findMember(member.getProvider(), member.getUid(), MemberStatus.ACTIVE)
// 			.ifPresent(m -> {
// 				throw new IllegalArgumentException("이미 존재하는 회원입니다.");
// 			});
// 	}
// }